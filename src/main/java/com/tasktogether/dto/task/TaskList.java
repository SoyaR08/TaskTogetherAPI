package com.tasktogether.dto.task;

import java.util.List;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.Projectmindto;
import com.tasktogether.model.Task;


public class TaskList {

	private Long id;
	
	private String name;
	
	private String description;
	
	private String limitDate;
	
	private Integer status;
	
	private Integer priority;
	
	private Projectmindto project;
	
	private MinUserInfo userCreator;
	
	private List<MinUserInfo> workers;

	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskList(Long id, String name, String description, String limitDate, Integer status, Integer priority,
			Projectmindto project, MinUserInfo userCreator, List<MinUserInfo> workers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.limitDate = limitDate;
		this.status = status;
		this.priority = priority;
		this.project = project;
		this.userCreator = userCreator;
		this.workers = workers;
	}
	
	public TaskList(Task t) {
		super();
		this.id = t.getId();
		this.name = t.getName();
		this.description = t.getDescription();
		this.limitDate = t.getLimitDate().toString();
		this.status = t.getStatus();
		this.priority = t.getPriority();
		this.project = new Projectmindto(t.getProject());
		this.userCreator = new MinUserInfo(t.getUserCreator());
	}
	
	public TaskList(Task t, List<MinUserInfo> workers) {
		super();
		this.id = t.getId();
		this.name = t.getName();
		this.description = t.getDescription();
		this.limitDate = t.getLimitDate().toString();
		this.status = t.getStatus();
		this.priority = t.getPriority();
		this.project = new Projectmindto(t.getProject());
		this.userCreator = new MinUserInfo(t.getUserCreator());
		this.workers = workers;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Projectmindto getProject() {
		return project;
	}

	public void setProject(Projectmindto project) {
		this.project = project;
	}

	public MinUserInfo getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(MinUserInfo userCreator) {
		this.userCreator = userCreator;
	}

	public List<MinUserInfo> getWorkers() {
		return workers;
	}

	public void setWorkers(List<MinUserInfo> workers) {
		this.workers = workers;
	}
	
	
	
}
