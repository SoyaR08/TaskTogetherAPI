package com.tasktogether.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
@Schema(description = "Entidad que representa un usuario en la aplicación")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID del usuario", example = "1")
	private Long id;

	@Column(name = "nombre")
	@Schema(description = "Nombre del usuario", example = "Juan")
	private String name;
	@Column(name = "role")
	@Schema(description = "Rol del usuario", example = "USER")
	private String role;
	@Column(name = "address")
	@Schema(description = "Dirección del usuario", example = "Calle imaginaria")
	private String address;
	@Column(name = "email")
	@Schema(description = "Email del usuario", example = "ejemplo@gmail.com")
	private String email;
	@Column(name = "password")
	@Schema(description = "Contraseña del usuario", example = "123456")
	private String password;
	@Column(name = "job")
	@Schema(description = "Trabajo del usuario", example = "Abogado")
	private String job;
	
	@Column(name = "profile_pic")
	@Schema(description = "Url de la foto de perfil del usuario subida"
			+ "a Cloudinary", example = "https://cloudinary")
	private String profile_pic;
	
	@OneToMany(mappedBy = "userCreator", cascade = CascadeType.ALL, orphanRemoval = true)
	@ArraySchema(schema = @Schema(implementation = Project.class, requiredMode = RequiredMode.REQUIRED))
	private List<Project> projects;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@ArraySchema(schema = @Schema(implementation = Member.class, requiredMode = RequiredMode.REQUIRED))
	private List<Member> memberOf;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String role, String address, String email, String password, String job) {
		super();
		this.name = name;
		this.role = role;
		this.address = address;
		this.email = email;
		this.password = password;
		this.job = job;
	}

	

	public User(Long id, String name, String role, String address, String email, String password, String job,
			String profile_pic) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.address = address;
		this.email = email;
		this.password = password;
		this.job = job;
		this.profile_pic = profile_pic;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}

}
