package com.tasktogether.dto.taskuser;

public class Taskuseradd {

	private Long taskId;

	private Long userId;

	public Taskuseradd() {
		super();
	}

	public Taskuseradd(Long taskId, Long userId) {
		super();
		this.taskId = taskId;
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
