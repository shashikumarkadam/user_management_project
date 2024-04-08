package com.example.model;


public enum Permission {
	

	    ADMIN_READ("admin:read"),
	    ADMIN_UPDATE("admin:update"),
	    ADMIN_CREATE("admin:create"),
	    ADMIN_DELETE("admin:delete"),
	    MANAGER_READ("management:read"),
	   
	    ;

	    private Permission(String permission) {
			this.permission = permission;
		}

		
	    private final String permission;

		public String getPermission() {
			return permission;
		}
		
}
