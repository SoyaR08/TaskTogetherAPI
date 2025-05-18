package com.tasktogether.dto.user;

import com.tasktogether.model.Member;

public class UserMember {

	private Long id;
	
	private String name;
	
	private String email;
	
	private String projectRole;
	
	private String profile_pic;

	public UserMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMember(Long id, String name, String email, String projectRole, String profile_pic) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.projectRole = projectRole;
		this.profile_pic = profile_pic;
	}
	
	public UserMember(Member m) {
		super();
		this.id = m.getUser().getId();
		this.name = m.getUser().getName();
		this.email = m.getUser().getEmail();
		this.projectRole = m.getUserRol();
		this.profile_pic = m.getUser().getProfile_pic();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	
	
	
}
