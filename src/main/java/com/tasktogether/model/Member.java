package com.tasktogether.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Miembros")
@IdClass(MemberID.class)
@Schema(description = "Entidad que representa a un miembro en la aplicación")
public class Member {

	@Id
	@ManyToOne
	@JoinColumn(name = "projectId")
	@Schema(description = "Proyecto al que pertenece", example = "1")
	private Project project;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "userId")
	@Schema(description = "Miembro del proyecto", example = "Juanito")
	private User user;
	
	@Column(name = "userRol")
	@Schema(description = "Rol del usuario miembro", example = "admin")
	private String userRol;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(Project project, User user, String userRol) {
		super();
		this.project = project;
		this.user = user;
		this.userRol = userRol;
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

	public String getUserRol() {
		return userRol;
	}

	public void setUserRol(String userRol) {
		this.userRol = userRol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(project, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(project, other.project) && Objects.equals(user, other.user);
	}
	
	
	
}
