package com.example.model;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.model.Permission.ADMIN_CREATE;
import static com.example.model.Permission.ADMIN_DELETE;
import static com.example.model.Permission.ADMIN_READ;
import static com.example.model.Permission.ADMIN_UPDATE;
import static com.example.model.Permission.MANAGER_READ;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
	
	USER(Collections.emptySet()), ADMIN(Set.of(ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE, ADMIN_CREATE, MANAGER_READ)),
	MANAGER(Set.of(MANAGER_READ));

	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	private final Set<Permission> permissions;

	
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}

}