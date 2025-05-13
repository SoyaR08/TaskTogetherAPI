package com.tasktogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Task;
import com.tasktogether.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskData;
	
	public List<Task> getAllTasks() {
		return taskData.findAll();
	}
	
}
