package com.tasktogether.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectSimpleDTO;
import com.tasktogether.model.Project;
import com.tasktogether.model.User;
import com.tasktogether.repository.ProjectRepository;
import com.tasktogether.repository.UserRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectData;
	
	@Autowired
	UserRepository userData;
	
	@Autowired
	UserService userMethods;
	
	public List<Project> getProjects() {
		return projectData.findAll();
	}
	
	public Project findProject(Long id) {
		return projectData.findById(id).orElse(null);
	}
	
	public User findOwner(String email) {
		List<User> user = userData.findByEmailLike(email);
		return user.size() > 0 ? user.get(0) : null;
	}
	
	public Page<Project> getInProgressUserProjects(User u, Pageable pageable) {
		return projectData.findByStatusAndUserCreator("IN_PROGRESS", u, pageable);
	}
	
	public Project add(ProjectAddDTO p) {
		
		User u = userMethods.findUser(p.getUserCreator());
		Project prt = new Project(p, LocalDate.parse(p.getStart_date()), LocalDate.parse(p.getEnd_date()), u);
		return projectData.save(prt);
	}
	
	public Project finish(Project p) {
		return projectData.save(p);
	}
	

	public Project parseAddToProject(ProjectAddDTO p) {
		User u = userMethods.findUser(p.getUserCreator());
		Project prt = new Project(p, LocalDate.parse(p.getStart_date()), LocalDate.parse(p.getEnd_date()), u);
		return prt;
	}
	
	public List<ProjectSimpleDTO> parseToSimpleDTO(List<Project> lp) {
		
		List<ProjectSimpleDTO> simpleList = lp.stream().map(project -> new ProjectSimpleDTO(project)).toList();
		return simpleList;
	}
	
}
