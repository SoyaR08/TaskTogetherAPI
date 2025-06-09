package com.tasktogether.dto.user;

import com.tasktogether.model.User;

public class UserCommentChat {

	private Long id;
	
	private String name;
	
	private String email;
	
	private String profile_pic;

	public UserCommentChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCommentChat(Long id, String name, String email, String profile_pic) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.profile_pic = profile_pic;
	}

	public UserCommentChat(User u) {
		super();
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.profile_pic = u.getProfile_pic();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	
	
	
}
