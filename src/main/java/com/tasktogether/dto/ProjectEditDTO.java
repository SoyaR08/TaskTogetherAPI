package com.tasktogether.dto;

public class ProjectEditDTO {

	private Long id;
	
	private String name;
	
	private String description;
	
	private String start_date;
	
	private String end_date;
	
	private String status;
	
	private String project_img;
	
	private Long userCreator;

	public ProjectEditDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectEditDTO(Long id, String name, String description, String start_date, String end_date, String status,
			String project_img, Long userCreator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.project_img = project_img;
		this.userCreator = userCreator;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProject_img() {
		return project_img;
	}

	public void setProject_img(String project_img) {
		this.project_img = project_img;
	}

	public Long getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(Long userCreator) {
		this.userCreator = userCreator;
	}
	
	
	
}
