package com.example.security.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static com.example.model.Permission.ADMIN_CREATE;
import static com.example.model.Permission.ADMIN_DELETE;
import static com.example.model.Permission.ADMIN_READ;
import static com.example.model.Permission.ADMIN_UPDATE;
import static com.example.model.Permission.MANAGER_READ;
import static com.example.model.Role.ADMIN;
import static com.example.model.Role.MANAGER;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	 private final JwtAuthenticationFilter jwtAuthFilter;
	    private final AuthenticationProvider authenticationProvider;

	    
	    
	    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
			super();
			this.jwtAuthFilter = jwtAuthFilter;
			this.authenticationProvider = authenticationProvider;
		}



		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                .and()
	                .csrf()
	                .disable()
	                .authorizeHttpRequests()
	                .requestMatchers(
	                        "/api/v1/auth/**",
	                        "/v2/api-docs",
	                        "/v3/api-docs",
	                        "/v3/api-docs/**",
	                        "/swagger-resources",
	                        "/swagger-resources/**",
	                        "/configuration/ui",
	                        "/configuration/security",
	                        "/swagger-ui/**",
	                        "/webjars/**",
	                        "/swagger-ui.html"
	                )
	                .permitAll()

	                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())

	                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
	                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name())
	                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name())
	                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name())

	                .anyRequest()
	                .authenticated()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .authenticationProvider(authenticationProvider)
	                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        ;

	        return http.build();
	    }
	}