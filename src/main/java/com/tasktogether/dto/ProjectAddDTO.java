package com.tasktogether.dto;

public class ProjectAddDTO {

	/*
	 * Esta clase ha sido creada para añadir proyectos
	 */

	private String name;
	private String description;
	private String start_date;
	private String end_date;
	private String status;
	private Long userCreator;

	public ProjectAddDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectAddDTO(String name, String description, String start_date, String end_date, String status,
			Long user_creator) {
		super();
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.userCreator = user_creator;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(Long user_creator) {
		this.userCreator = user_creator;
	}

}
