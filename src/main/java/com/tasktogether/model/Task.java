package com.tasktogether.model;

import java.time.LocalDate;
import java.util.Objects;

import com.tasktogether.dto.task.TaskAdd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tarea")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "limit_date")
	private LocalDate limitDate;

	@Column(name = "status")
	private String status;

	@Column(name = "priority")
	private String priority;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "user_creator")
	private User userCreator;

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(TaskAdd t, Project project,
			User userCreator) {
		super();
		this.name = t.getName();
		this.description = t.getDescription();
		this.limitDate = LocalDate.parse(t.getLimitDate());
		this.status = t.getStatus();
		this.priority = t.getPriority();
		this.project = project;
		this.userCreator = userCreator; 
	}
	
	public Task(Long id, String name, String description, String status, String priority, Project project,
			User userCreator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.project = project;
		this.userCreator = userCreator;
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

	public LocalDate getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}

}
