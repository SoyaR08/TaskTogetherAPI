package com.tasktogether.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TareaUsuario")
@IdClass(TaskUserID.class)
public class TaskUser {

	@Id
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task task;

	public TaskUser() {
		super();
	}

	public TaskUser(User user, Task task) {
		super();
		this.user = user;
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
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
		TaskUser other = (TaskUser) obj;
		return Objects.equals(task, other.task) && Objects.equals(user, other.user);
	}

	
	
}
