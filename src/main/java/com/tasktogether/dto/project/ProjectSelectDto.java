package com.tasktogether.dto.project;

import com.tasktogether.model.Project;

public class ProjectSelectDto {

	private Long id;

	private String name;

	public ProjectSelectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectSelectDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ProjectSelectDto(Project p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
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

}
