package com.tasktogether.dto.comment;

import com.tasktogether.dto.user.UserCommentChat;
import com.tasktogether.model.Comment;

public class CommentList {

	private Long id;
	
	private String content;
	
	private String date;
	
	private UserCommentChat user;
	
	private Long taskId;

	private boolean owner;
	
	public CommentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CommentList(Long id, String content, String date, UserCommentChat user, Long taskId, boolean owner) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.user = user;
		this.taskId = taskId;
		this.owner = owner;
	}



	public CommentList(Comment c) {
		super();
		this.id = c.getId();
		this.content = c.getContent();
		this.date = c.getDate().toString();
		this.user = new UserCommentChat(c.getUserId());
		this.taskId = c.getTaskId().getId();
	}
	
	public CommentList(Comment c, boolean owner) {
		super();
		this.id = c.getId();
		this.content = c.getContent();
		this.date = c.getDate().toString();
		this.user = new UserCommentChat(c.getUserId());
		this.taskId = c.getTaskId().getId();
		this.owner = owner;
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

	public UserCommentChat getUser() {
		return user;
	}



	public void setUser(UserCommentChat user) {
		this.user = user;
	}



	public boolean isOwner() {
		return owner;
	}



	public void setOwner(boolean owner) {
		this.owner = owner;
	}



	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
	
}
