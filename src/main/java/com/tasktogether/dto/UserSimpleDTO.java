package com.tasktogether.dto;

import com.tasktogether.model.User;

public class UserSimpleDTO {

	/*
	 * He creado esta clase para mostrar la 
	 * información justa al añadir proyectos*/
	private Long id;
	
	private String name;
	
	private String username;
	
	private String role;

	public UserSimpleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSimpleDTO(Long id, String name, String username, String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.role = role;
	}
	
	public UserSimpleDTO(User u) {
		super();
		this.id = u.getId();
		this.name = u.getName();
		this.username = u.getEmail();
		this.role = u.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
