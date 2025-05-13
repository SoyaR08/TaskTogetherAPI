package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
