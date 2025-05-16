package com.tasktogether.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Historial")
public class Historical {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "changeDescription")
	private String changeDescription;

	@Column(name = "changeDate")
	private LocalDateTime changeDate;

	public Historical() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Historical(Project project, User user, String changeDescription, LocalDateTime changeDate) {
		super();
		this.project = project;
		this.user = user;
		this.changeDescription = changeDescription;
		this.changeDate = changeDate;
	}

	public Historical(Long id, Project project, User user, String changeDescription, LocalDateTime changeDate) {
		super();
		this.id = id;
		this.project = project;
		this.user = user;
		this.changeDescription = changeDescription;
		this.changeDate = changeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public LocalDateTime getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDateTime changeDate) {
		this.changeDate = changeDate;
	}

}
