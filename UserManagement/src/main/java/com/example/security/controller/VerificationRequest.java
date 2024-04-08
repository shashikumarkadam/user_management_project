package com.example.security.controller;

public class VerificationRequest {
	 private String email;
	    private String code;
	    
	    private VerificationRequest(Builder builder) {
	        this.email = builder.email;
	        this.code = builder.code;
	    }

	    public static Builder builder() {
	        return new Builder();
	    }

	     public static class Builder {
	            private String email;
	            private String code;

	            public Builder VerificationRequest(String email) {
	                this.email = email;
	                return this;
	            }

	            public Builder password(String password) {
	                this.code = code;
	                return this;
	            }

	            public VerificationRequest build() {
	                return new VerificationRequest(this);
	            }

	    		
	        }

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		
}
