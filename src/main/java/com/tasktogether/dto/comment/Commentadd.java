package com.tasktogether.dto.comment;

import java.time.LocalDate;

public class Commentadd {

	private String content;
	
	private String date;
	
	private Long userId;
	
	private Long taskId;

	public Commentadd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentadd(String content, String date, Long userId, Long taskId) {
		super();
		this.content = content;
		this.date = date;
		this.userId = userId;
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
	
}
