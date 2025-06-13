package com.tasktogether.dto;

import java.util.List;

import com.tasktogether.dto.project.ProjectCard;
import com.tasktogether.dto.task.TaskEsentialDetails;

public class UserDashboard {

	private Integer activeProjectsNumber;

	private Integer progressTasksNumber;

	private Integer toExpireTasksNumber;

	private List<TaskEsentialDetails> progressTasks;

	private List<TaskEsentialDetails> toExpireTasks;

	private List<ProjectCard> recentProyects;

	public UserDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDashboard(Integer activeProjectsNumber, Integer progressTasksNumber, Integer toExpireTasksNumber,
			List<TaskEsentialDetails> progressTasks, List<TaskEsentialDetails> toExpireTasks,
			List<ProjectCard> recentProyects) {
		super();
		this.activeProjectsNumber = activeProjectsNumber;
		this.progressTasksNumber = progressTasksNumber;
		this.toExpireTasksNumber = toExpireTasksNumber;
		this.progressTasks = progressTasks;
		this.toExpireTasks = toExpireTasks;
		this.recentProyects = recentProyects;
	}

	public Integer getActiveProjectsNumber() {
		return activeProjectsNumber;
	}

	public void setActiveProjectsNumber(Integer activeProjectsNumber) {
		this.activeProjectsNumber = activeProjectsNumber;
	}

	public Integer getProgressTasksNumber() {
		return progressTasksNumber;
	}

	public void setProgressTasksNumber(Integer progressTasksNumber) {
		this.progressTasksNumber = progressTasksNumber;
	}

	public Integer getToExpireTasksNumber() {
		return toExpireTasksNumber;
	}

	public void setToExpireTasksNumber(Integer toExpireTasksNumber) {
		this.toExpireTasksNumber = toExpireTasksNumber;
	}

	public List<ProjectCard> getRecentProyects() {
		return recentProyects;
	}

	public void setRecentProyects(List<ProjectCard> recentProyects) {
		this.recentProyects = recentProyects;
	}

	public List<TaskEsentialDetails> getProgressTasks() {
		return progressTasks;
	}

	public void setProgressTasks(List<TaskEsentialDetails> progressTasks) {
		this.progressTasks = progressTasks;
	}

	public List<TaskEsentialDetails> getToExpireTasks() {
		return toExpireTasks;
	}

	public void setToExpireTasks(List<TaskEsentialDetails> toExpireTasks) {
		this.toExpireTasks = toExpireTasks;
	}

}
