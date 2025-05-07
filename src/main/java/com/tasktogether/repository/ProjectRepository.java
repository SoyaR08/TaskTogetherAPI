package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
