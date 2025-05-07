package com.tasktogether.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectSimpleDTO;
import com.tasktogether.model.Project;
import com.tasktogether.model.User;
import com.tasktogether.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectData;
	
	@Autowired
	UserService userMethods;
	
	public List<Project> getProjects() {
		return projectData.findAll();
	}
	
	public Project add(ProjectAddDTO p) {
		
		User u = userMethods.findUser(p.getUser_creator());
		Project prt = new Project(p, LocalDate.parse(p.getStart_date()), LocalDate.parse(p.getEnd_date()), u);
		return projectData.save(prt);
	}
	

	public List<ProjectSimpleDTO> parseToSimpleDTO(List<Project> lp) {
		
		List<ProjectSimpleDTO> simpleList = lp.stream().map(project -> new ProjectSimpleDTO(project)).toList();
		return simpleList;
	}
	
}
