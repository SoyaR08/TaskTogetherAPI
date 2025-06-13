package com.tasktogether.dto.project;

import com.tasktogether.model.Project;

public class ProjectCard {

	private Long id;
	
	private String name;
	
	private Integer status;
	
	private String project_img;

	public ProjectCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectCard(Long id, String name, Integer status, String project_img) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.project_img = project_img;
	}
	
	public ProjectCard(Project p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.status = p.getStatus();
		this.project_img = p.getProject_img();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProject_img() {
		return project_img;
	}

	public void setProject_img(String project_img) {
		this.project_img = project_img;
	}
	
	
	
}
