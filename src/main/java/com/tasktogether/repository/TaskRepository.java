package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Project;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserCreator(User userCreator); //Posiblemente lo quite
	List<Task> findByProjectAndStatusNot(Project project, Integer status);
}
