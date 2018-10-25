package com.dd.authentication.security;

import java.util.List;

public class User {
	
	private String user;
	private String password;
	private List<String> roles;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [user=" + user + ", password=" + password + ", roles=" + roles + "]";
	}
	
	

}
