package com.tasktogether.dto;

import java.time.LocalDate;

import com.tasktogether.model.Project;

public class ProjectMinDTO {

	/*
	 * He creado esta clase para devolver los datos de manera 
	 * simplificada al añadir un proyecto*/
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private LocalDate start_date;
	
	private LocalDate end_date;
	
	private UserSimpleDTO user_creator;

	public ProjectMinDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectMinDTO(Long id, String name, String description, LocalDate start_date, LocalDate end_date,
			UserSimpleDTO user_creator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_creator = user_creator;
	}
	
	public ProjectMinDTO(Project p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.start_date = p.getStart_date();
		this.end_date = p.getEnd_date();
		this.user_creator = new UserSimpleDTO(p.getUser_creator());
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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public UserSimpleDTO getUser_creator() {
		return user_creator;
	}

	public void setUser_creator(UserSimpleDTO user_creator) {
		this.user_creator = user_creator;
	}
	
	
	
}
