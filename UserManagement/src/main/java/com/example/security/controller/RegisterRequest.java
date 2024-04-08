package com.example.security.controller;

import com.example.model.Role;

public class RegisterRequest {

	  private String firstname;
	  private String lastname;
	  private String email;
	  private String password;
	  private Role role;
	  private boolean mfaEnabled;
	  
	  
	public RegisterRequest() {
	}
	public RegisterRequest(String firstname, String lastname, String email, String password, Role role,
			boolean mfaEnabled) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.mfaEnabled = mfaEnabled;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isMfaEnabled() {
		return mfaEnabled;
	}
	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}
	  

	private RegisterRequest(Builder builder) {
		this.firstname=builder.firstname;
		this.lastname = builder.lastname;
		this.role = builder.role;
	    this.email = builder.email;
	    this.password = builder.password;
	    this.mfaEnabled = builder.mfaEnabled;
	}

	public static Builder builder() {
	    return new Builder();
	}

	 public static class Builder {
		 private String firstname;
		 private String lastname;
		 private Role role;
		 private String email;
	     private String password;
		 private boolean mfaEnabled;

	        public Builder RegisterRequest(String email) {
	            this.email = email;
	            return this;
	        }
	        public Builder firstname(String firstname) {
	            this.firstname = firstname;
	            return this;
	        }
	        public Builder lastname(String lastname) {
	            this.lastname = lastname;
	            return this;
	        }
	        public Builder role(Role role) {
	            this.role = role;
	            return this;
	        }

	        public Builder password(String password) {
	            this.password = password;
	            return this;
	        }
	        public Builder mfaEnabled(boolean mfaEnabled) {
	            this.mfaEnabled = mfaEnabled;
	            return this;
	        }
	        
	        public RegisterRequest build() {
	            return new RegisterRequest(this);
	        }

			
	    }
}
