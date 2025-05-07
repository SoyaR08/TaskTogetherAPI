package com.tasktogether.dto;

import com.tasktogether.model.Project;

public class ProjectSimpleDTO {

	/*
	 * He creado esta clase para mostrar un mínimo de información de los proyectos
	 * al listar los usuarios
	 * */
	
	private Long id;
	
	private String name;

	public ProjectSimpleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectSimpleDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProjectSimpleDTO(Project p) {
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
