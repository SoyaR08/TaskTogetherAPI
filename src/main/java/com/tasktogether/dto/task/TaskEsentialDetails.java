package com.tasktogether.dto.task;

import com.tasktogether.model.Task;

public class TaskEsentialDetails {

	private Long id;
	
	private String name;
	
	private String limitDate;
	
	private Integer priority;
	
	private Integer status;

	public TaskEsentialDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskEsentialDetails(Long id, String name, String limitDate, Integer priority, Integer status) {
		super();
		this.id = id;
		this.name = name;
		this.limitDate = limitDate;
		this.priority = priority;
		this.status = status;
	}
	
	public TaskEsentialDetails(Task t) {
		super();
		this.id = t.getId();
		this.name = t.getName();
		this.limitDate = t.getLimitDate().toString();
		this.priority = t.getPriority();
		this.status = t.getStatus();
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

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
