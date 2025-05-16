package com.tasktogether.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.task.TaskAdd;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.model.Historical;
import com.tasktogether.model.Task;
import com.tasktogether.service.HistoricalService;
import com.tasktogether.service.TaskService;
//import com.tasktogether.service.transform.TaskTransformService;

@RestController
public class TaskController {

	@Autowired
	TaskService taskMethods;

	@Autowired
	HistoricalService historicalMethods;
	
//	@Autowired
//	TaskTransformService taskParseMethods;

	@GetMapping("/tasks")
	public ResponseEntity<?> getTasks() {
		List<TaskList> body = taskMethods.returnTaskList(taskMethods.getAllTasks());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@PostMapping("/tasks/add")
	public ResponseEntity<?> addTask(@RequestHeader("Authorization") String token, @RequestBody TaskAdd t) {
		Map<String, String> response = new HashMap<>();

		try {
			 
			Task newtask = taskMethods.add(t);
			Historical h = historicalMethods.addNewTask(newtask);
			response.put("message", "Tarea agregada con éxito");

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.put("error", "500");
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

}
