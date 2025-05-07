package com.tasktogether.model;

import java.io.Serializable;

public class MemberID implements Serializable{

	private Long project;
	
	private Long user;

	public MemberID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberID(Long project, Long user) {
		super();
		this.project = project;
		this.user = user;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
	
	
	
}
