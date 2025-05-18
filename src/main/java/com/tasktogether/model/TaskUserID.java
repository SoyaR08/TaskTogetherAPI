package com.tasktogether.model;

import java.io.Serializable;
import java.util.Objects;

public class TaskUserID implements Serializable {

	private Long user;

	private Long task;

	public TaskUserID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskUserID(Long user, Long task) {
		super();
		this.user = user;
		this.task = task;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getTask() {
		return task;
	}

	public void setTask(Long task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		return Objects.hash(task, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskUserID other = (TaskUserID) obj;
		return Objects.equals(task, other.task) && Objects.equals(user, other.user);
	}

}
