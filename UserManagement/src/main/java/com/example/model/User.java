package com.example.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "user")
public class User implements UserDetails{

	 @Id
	  @GeneratedValue
	  private Integer id;
	  private String firstname;
	  private String lastname;
	  private String email;
	  private String password;
	  private boolean mfaEnabled;
	  private String secret;

	  
	  
	  public User() {
	}

	public User(Integer id, String firstname, String lastname, String email, String password, boolean mfaEnabled,
			String secret, Role role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.mfaEnabled = mfaEnabled;
		this.secret = secret;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isMfaEnabled() {
		return mfaEnabled;
	}

	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	  private Role role;

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return role.getAuthorities();
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
	  
	  
	  
	  private User(Builder builder) {
	      this.firstname = builder.firstname;
	      this.lastname = builder.lastname;
	      this.email = builder.email;
	      this.password = builder.password;
	      this.mfaEnabled = builder.mfaEnabled;
	      this.secret = builder.secret;
	      this.role = builder.role;
	  }

	 

	public static Builder builder() {
	    return new Builder();
	}

	 public static class Builder {
		  private String firstname;
		  private String lastname;
		  private String email;
		  private String password;
		  private boolean mfaEnabled;
		  private String secret;
		  private Role role;
	        public Builder firstname(String firstname) {
	            this.firstname = firstname;
	            return this;
	        }

	        public Builder lastname(String lastname) {
	            this.lastname = lastname;
	            return this;
	        }
	        public Builder email(String email) {
	            this.email = email;
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
	        public Builder secret(String secret) {
	            this.secret = secret;
	            return this;
	        }
	        public Builder role(Role role) {
	            this.role = role;
	            return this;
	        }

	        public User build() {
	            return new User(this);
	        }
	 }
			
	
}
