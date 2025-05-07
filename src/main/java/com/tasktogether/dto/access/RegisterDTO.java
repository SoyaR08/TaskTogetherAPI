package com.tasktogether.dto.access;

public class RegisterDTO {

	private String name;
	
	private String address;
	
	private String email;
	
	private String password;
	
	private String job;

	public RegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterDTO(String name, String address, String email, String password, String job) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
}
