package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.TaskUser;
import com.tasktogether.model.TaskUserID;

public interface TaskUserRepository extends JpaRepository<TaskUser, TaskUserID>{
	
}
