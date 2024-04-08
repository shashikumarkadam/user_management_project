package com.example.security.controller;


public class AuthenticationRequest {
	 private String email;
	  private String password;
	  
	  
	public AuthenticationRequest() {
	}
	public AuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	  
	 
	private AuthenticationRequest(Builder builder) {
	    this.email = builder.email;
	    this.password = builder.password;
	}

	public static Builder builder() {
	    return new Builder();
	}

	 public static class Builder {
	        private String email;
	        private String password;

	        public Builder AuthenticationRequest(String email) {
	            this.email = email;
	            return this;
	        }

	        public Builder password(String password) {
	            this.password = password;
	            return this;
	        }

	        public AuthenticationRequest build() {
	            return new AuthenticationRequest(this);
	        }

			
	    }
	}

