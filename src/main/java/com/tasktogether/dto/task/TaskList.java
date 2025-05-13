package com.tasktogether.dto.task;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.ProjectMinDTO;
import com.tasktogether.model.Task;


public class TaskList {

	private Long id;
	
	private String name;
	
	private String description;
	
	private String limitDate;
	
	private String status;
	
	private String priority;
	
	private ProjectMinDTO project;
	
	private MinUserInfo userCreator;

	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskList(Long id, String name, String description, String limitDate, String status, String priority,
			ProjectMinDTO project, MinUserInfo userCreator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.limitDate = limitDate;
		this.status = status;
		this.priority = priority;
		this.project = project;
		this.userCreator = userCreator;
	}
	
	public TaskList(Task t) {
		super();
		this.id = t.getId();
		this.name = t.getName();
		this.description = t.getDescription();
		this.limitDate = t.getLimitDate().toString();
		this.status = t.getStatus();
		this.priority = t.getPriority();
		this.project = new ProjectMinDTO(t.getProject());
		this.userCreator = new MinUserInfo(t.getUserCreator());
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

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public ProjectMinDTO getProject() {
		return project;
	}

	public void setProject(ProjectMinDTO project) {
		this.project = project;
	}

	public MinUserInfo getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(MinUserInfo userCreator) {
		this.userCreator = userCreator;
	}
	
	
	
}
