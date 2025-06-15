package com.tasktogether.dto;

import com.tasktogether.model.Project;

public class Projectmindto {

	/*
	 * He creado esta clase para devolver los datos de manera simplificada al añadir
	 * un proyecto
	 */

	private Long id;

	private String name;

	private String description;

	private String start_date;

	private String end_date;

	private UserSimpleDTO user_creator;

	public Projectmindto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projectmindto(Long id, String name, String description, String start_date, String end_date,
			UserSimpleDTO user_creator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_creator = user_creator;
	}

	public Projectmindto(Project p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.start_date = p.getStart_date().toString();
		this.end_date = p.getEnd_date().toString();
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

	public UserSimpleDTO getUser_creator() {
		return user_creator;
	}

	public void setUser_creator(UserSimpleDTO user_creator) {
		this.user_creator = user_creator;
	}

}
