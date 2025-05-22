package com.tasktogether.dto.task;

public class TaskAdd {

	
	private String name;

	
	private String description;


	private String limitDate;


	private Integer status;


	private String priority;


	private Long projectId;


	private Long userCreator;


	public TaskAdd() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TaskAdd(String name, String description, String limitDate, Integer status, String priority, Long projectId,
			Long userCreator) {
		super();
		this.name = name;
		this.description = description;
		this.limitDate = limitDate;
		this.status = status;
		this.priority = priority;
		this.projectId = projectId;
		this.userCreator = userCreator;
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


	public String getLimitDate() {
		return limitDate;
	}


	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public Long getUserCreator() {
		return userCreator;
	}


	public void setUserCreator(Long userCreator) {
		this.userCreator = userCreator;
	}
	
	
	
}
