package com.tasktogether.dto.task;

public class Taskchangestatus {

	private Long id;
	
	private Integer status;

	public Taskchangestatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Taskchangestatus(Long id, Integer status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
