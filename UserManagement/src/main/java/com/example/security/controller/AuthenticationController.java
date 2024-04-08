package com.example.security.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationController {
	 private final AuthenticationService service;
	    
	    
	    public AuthenticationController(AuthenticationService service) {
			super();
			this.service = service;
		}

		@PostMapping("/register")
	    public ResponseEntity<?> register(
	            @RequestBody RegisterRequest request
	    ) {
	        var response = service.register(request);
	        if (request.isMfaEnabled()) {
	            return ResponseEntity.ok(response);
	        }
	        return ResponseEntity.accepted().build();
	    }

	    @PostMapping("/authenticate")
	    public ResponseEntity<AuthenticationResponse> authenticate(
	            @RequestBody AuthenticationRequest request
	    ) {
	        return ResponseEntity.ok(service.authenticate(request));
	    }

	    @PostMapping("/refresh-token")
	    public void refreshToken(
	            HttpServletRequest request,
	            HttpServletResponse response
	    ) throws IOException {
	        service.refreshToken(request, response);
	    }

	    @PostMapping("/verify")
	    public ResponseEntity<?> verifyCode(
	            @RequestBody VerificationRequest verificationRequest
	    ) {
	        return ResponseEntity.ok(service.verifyCode(verificationRequest));
	    }


	}

