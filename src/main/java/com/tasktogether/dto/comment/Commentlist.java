package com.tasktogether.dto.comment;

import com.tasktogether.model.Comment;

public class Commentlist {

	private Long id;
	
	private String content;
	
	private String date;
	
	private Long userId;
	
	private Long taskId;

	public Commentlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentlist(Long id, String content, String date, Long userId, Long taskId) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.userId = userId;
		this.taskId = taskId;
	}

	public Commentlist(Comment c) {
		super();
		this.id = c.getId();
		this.content = c.getContent();
		this.date = c.getDate().toString();
		this.userId = c.getUserId().getId();
		this.taskId = c.getTaskId().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
