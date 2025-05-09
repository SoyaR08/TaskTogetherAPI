package com.tasktogether.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Project;
import com.tasktogether.model.User;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	public Page<Project> findByStatusAndUserCreator(String status, User user_creator, 
			Pageable pageable);
	
}
