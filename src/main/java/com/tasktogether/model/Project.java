package com.tasktogether.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectEditDTO;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Proyecto")
@Schema(description = "Entidad que representa un proyecto en la aplicación")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID del proyecto", example = "1")
	private Long id;
	@Column(name = "name")
	@Schema(description = "Nombre del proyecto", example = "Proyecto 1")
	private String name;
	@Column(name = "description")
	@Schema(description = "Descripción del proyecto", example = "Ejemplo de descripción")
	private String description;
	@Column(name = "start_date")
	@Schema(description = "Fecha de inicio del proyecto", example = "14/01/2025")
	private LocalDate start_date;
	@Column(name = "end_date")
	@Schema(description = "Fecha de fin del proyecto", example = "14/06/2025")
	private LocalDate end_date;
	@Column(name = "status")
	@Schema(description = "Estado del proyecto", example = "0")
	private Integer status;
	@Column(name = "project_img")
	@Schema(description = "Portada del proyecto")
	private String project_img;
	@ManyToOne
	@JoinColumn(name = "user_creator")
	@Schema(description = "Usuario que creo el proyecto", example = "admin")
	private User userCreator;
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	@ArraySchema(schema = @Schema(implementation = Member.class, requiredMode = RequiredMode.REQUIRED))
	private List<Member> members;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Historical> historical;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(Long id, String name, String description, LocalDate start_date, LocalDate end_date, Integer status,
			String project_img, User user_creator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.project_img = project_img;
		this.userCreator = user_creator;
	}

	public Project(ProjectEditDTO p, User u) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.start_date = LocalDate.parse(p.getStart_date());
		this.end_date = LocalDate.parse(p.getEnd_date());
		this.status = p.getStatus();
		this.project_img = p.getProject_img();
		this.userCreator = u;

	}

	public Project(ProjectAddDTO p, LocalDate start_date, LocalDate end_date, User u) {
		super();
		this.name = p.getName();
		this.description = p.getDescription();
		this.start_date = start_date;
		this.end_date = start_date;
		this.status = p.getStatus();
		this.userCreator = u;
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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProject_img() {
		return project_img;
	}

	public void setProject_img(String project_img) {
		this.project_img = project_img;
	}

	public User getUser_creator() {
		return userCreator;
	}

	public void setUser_creator(User user_creator) {
		this.userCreator = user_creator;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Historical> getHistorical() {
		return historical;
	}

	public void setHistorical(List<Historical> historical) {
		this.historical = historical;
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
		Project other = (Project) obj;
		return Objects.equals(id, other.id);
	}

}
