package com.tasktogether.dto;

public class UserChangeRole {

	private Long id;
	
	private String newRole;

	public UserChangeRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserChangeRole(Long id, String newRole) {
		super();
		this.id = id;
		this.newRole = newRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewRole() {
		return newRole;
	}

	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}
	
	
	
}
