package com.tasktogether.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.task.TaskList;
import com.tasktogether.service.TaskService;
import com.tasktogether.service.transform.TaskTransformService;

@RestController
public class TaskController {

	@Autowired
	TaskService taskMethods;
	
	@Autowired
	TaskTransformService taskParseMethods;
	
	@GetMapping("/tasks")
	public ResponseEntity<?> getTasks() {
		List<TaskList> body = taskParseMethods.mapTasksToTaskList(taskMethods.getAllTasks());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	
}
