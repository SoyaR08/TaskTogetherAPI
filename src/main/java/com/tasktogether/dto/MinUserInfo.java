package com.tasktogether.dto;

import com.tasktogether.model.User;

public class MinUserInfo {

	/*
	 * He creado esta clase para 
	 * mostrar información importante del usuario
	 * en la vista de Administrador
	 * */
	
	private Long id;

	private String name;

	private String role;

	private String address;

	private String email;

	private String job;

	public MinUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MinUserInfo(User u) {
		super();
		this.id = u.getId();
		this.name = u.getName();
		this.role = u.getRole();
		this.address = u.getAddress();
		this.email = u.getEmail();
		this.job = u.getJob();
	}
	
	public MinUserInfo(Long id, String name, String role, String address, String email, String job) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.address = address;
		this.email = email;
		this.job = job;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
	
}
