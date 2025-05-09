package com.tasktogether.dto;

import com.tasktogether.model.Project;

public class ProjectListDTO {

	/**
	 * He creado esta clase para poder listar la información relevante de los
	 * proyectos
	 */

	private Long id;

	private String name;

	private String description;

	private String start_date;

	private String end_date;

	private String project_img;

	public ProjectListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectListDTO(Long id, String name, String description, String start_date, String end_date,
			String project_img) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.project_img = project_img;
	}
	
	public ProjectListDTO(Project p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.start_date = p.getStart_date().toString();
		this.end_date = p.getEnd_date().toString();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getProject_img() {
		return project_img;
	}

	public void setProject_img(String project_img) {
		this.project_img = project_img;
	}

}
