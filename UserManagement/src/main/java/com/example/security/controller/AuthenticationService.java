package com.example.security.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.config.JwtService;
import com.example.tfa.TwoFactorAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class AuthenticationService {
	 private final UserRepository repository;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtService jwtService;
	    private final AuthenticationManager authenticationManager;
	    private final TwoFactorAuthenticationService tfaService;

	    
	    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
				AuthenticationManager authenticationManager, TwoFactorAuthenticationService tfaService) {
			super();
			this.repository = repository;
			this.passwordEncoder = passwordEncoder;
			this.jwtService = jwtService;
			this.authenticationManager = authenticationManager;
			this.tfaService = tfaService;
		}

		public AuthenticationResponse register(RegisterRequest request) {
	        var user = User.builder()
	                .firstname(request.getFirstname())
	                .lastname(request.getLastname())
	                .email(request.getEmail())
	                .password(passwordEncoder.encode(request.getPassword()))
	                .role(Role.ADMIN)
	                .mfaEnabled(request.isMfaEnabled())
	                .build();

	        // if MFA enabled --> Generate Secret
	        if (request.isMfaEnabled()) {
	            user.setSecret(tfaService.generateNewSecret());
	        }
	        repository.save(user);
	        var jwtToken = jwtService.generateToken(user);
	        var refreshToken = jwtService.generateRefreshToken(user);
	        return AuthenticationResponse.builder()
	                .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
	                .accessToken(jwtToken)
	                .refreshToken(refreshToken)
	                .mfaEnabled(user.isMfaEnabled())
	                .build();
	    }

	    public AuthenticationResponse authenticate(AuthenticationRequest request) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()
	                )
	        );
	        var user = repository.findByEmail(request.getEmail())
	                .orElseThrow();
	        if (user.isMfaEnabled()) {
	            return AuthenticationResponse.builder()
	                    .accessToken("")
	                    .refreshToken("")
	                    .mfaEnabled(true)
	                    .build();
	        }
	        var jwtToken = jwtService.generateToken(user);
	        var refreshToken = jwtService.generateRefreshToken(user);
	        return AuthenticationResponse.builder()
	                .accessToken(jwtToken)
	                .refreshToken(refreshToken)
	                .mfaEnabled(false)
	                .build();
	    }

	    public void refreshToken(
	            HttpServletRequest request,
	            HttpServletResponse response
	    ) throws IOException {
	        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	        final String refreshToken;
	        final String userEmail;
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            return;
	        }
	        refreshToken = authHeader.substring(7);
	        userEmail = jwtService.extractUsername(refreshToken);
	        if (userEmail != null) {
	            var user = this.repository.findByEmail(userEmail)
	                    .orElseThrow();
	            if (jwtService.isTokenValid(refreshToken, user)) {
	                var accessToken = jwtService.generateToken(user);
	                var authResponse = AuthenticationResponse.builder()
	                        .accessToken(accessToken)
	                        .refreshToken(refreshToken)
	                        .mfaEnabled(false)
	                        .build();
	                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	            }
	        }
	    }

	    public AuthenticationResponse verifyCode(
	            VerificationRequest verificationRequest
	    ) {
	        User user = repository
	                .findByEmail(verificationRequest.getEmail())
	                .orElseThrow(() -> new EntityNotFoundException(
	                        String.format("No user found with %S", verificationRequest.getEmail()))
	                );
	        if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {

	            throw new BadCredentialsException("Code is not correct");
	        }
	        var jwtToken = jwtService.generateToken(user);
	        return AuthenticationResponse.builder()
	                .accessToken(jwtToken)
	                .mfaEnabled(user.isMfaEnabled())
	                .build();
	    }
	}
