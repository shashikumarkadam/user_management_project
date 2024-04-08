package com.example.security.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class AuthenticationResponse {
	 private String accessToken;
	  private String refreshToken;
	  private boolean mfaEnabled;
	  private String secretImageUri;
	public AuthenticationResponse(String accessToken, String refreshToken, boolean mfaEnabled, String secretImageUri) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.mfaEnabled = mfaEnabled;
		this.secretImageUri = secretImageUri;
	}
	public AuthenticationResponse() {
		super();
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public boolean isMfaEnabled() {
		return mfaEnabled;
	}
	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}
	public String getSecretImageUri() {
		return secretImageUri;
	}
	public void setSecretImageUri(String secretImageUri) {
		this.secretImageUri = secretImageUri;
	}
	  
	private AuthenticationResponse(Builder builder) {
	    this.accessToken = builder.accessToken;
	    this.refreshToken = builder.refreshToken;
	    this.mfaEnabled=builder.mfaEnabled;
	    this.secretImageUri = builder.secretImageUri;
	}

	public static Builder builder() {
	    return new Builder();
	}

	 public static class Builder {
		 private String accessToken;
		  private String refreshToken;
		  private boolean mfaEnabled;
		  private String secretImageUri;

	        public Builder accessToken(String accessToken) {
	            this.accessToken = accessToken;
	            return this;
	        }

	        public Builder refreshToken(String refreshToken) {
	            this.refreshToken = refreshToken;
	            return this;
	        }
	        public Builder mfaEnabled(boolean mfaEnabled) {
	            this.mfaEnabled = mfaEnabled;
	            return this;
	        }
	        public Builder secretImageUri(String secretImageUri) {
	            this.secretImageUri = secretImageUri;
	            return this;
	        }

	        public AuthenticationResponse build() {
	            return new AuthenticationResponse(this);
	        }

	 }
	}
