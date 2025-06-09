package com.tasktogether.dto.project;

import java.util.List;

import com.tasktogether.dto.task.TaskList;
import com.tasktogether.dto.user.UserMember;
import com.tasktogether.model.Project;


public class ProjectHome {

	private Long id;
	
	private String name;
	
	private List<UserMember> members; //Posiblemente elimine esto
	
	private List<TaskList> pending;
	
	private List<TaskList> progress;
	
	private List<TaskList> finished;

	public ProjectHome() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectHome(Long id, String name, List<UserMember> members, List<TaskList> pending, List<TaskList> progress,
			List<TaskList> finished) {
		super();
		this.id = id;
		this.name = name;
		this.members = members;
		this.pending = pending;
		this.progress = progress;
		this.finished = finished;
	}

	public ProjectHome(Project p, List<UserMember> members, List<TaskList> pending, List<TaskList> progress,
			List<TaskList> finished) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.members = members;
		this.pending = pending;
		this.progress = progress;
		this.finished = finished;
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

	public List<UserMember> getMembers() {
		return members;
	}

	public void setMembers(List<UserMember> members) {
		this.members = members;
	}

	public List<TaskList> getPending() {
		return pending;
	}

	public void setPending(List<TaskList> pending) {
		this.pending = pending;
	}

	public List<TaskList> getProgress() {
		return progress;
	}

	public void setProgress(List<TaskList> progress) {
		this.progress = progress;
	}

	public List<TaskList> getFinished() {
		return finished;
	}

	public void setFinished(List<TaskList> finished) {
		this.finished = finished;
	}
	
	
	
}
