package com.tasktogether.dto.task;

import com.tasktogether.model.Task;

public class TaskSelectDto {

	private Long id;
	
	private String name;

	public TaskSelectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskSelectDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public TaskSelectDto(Task t) {
		super();
		this.id = t.getId();
		this.name = t.getName();
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
