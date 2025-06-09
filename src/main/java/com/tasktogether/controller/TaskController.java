package com.tasktogether.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.task.TaskAdd;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.dto.task.TaskSelectDto;
import com.tasktogether.dto.task.Taskchangestatus;
import com.tasktogether.libraries.Defaultresponse;
import com.tasktogether.model.Project;
import com.tasktogether.model.Task;
import com.tasktogether.service.ProjectService;
import com.tasktogether.service.TaskService;
//import com.tasktogether.service.transform.TaskTransformService;
import com.tasktogether.service.transform.TaskTransformService;

@RestController
public class TaskController {

	@Autowired
	TaskService taskMethods;

	@Autowired
	ProjectService projectMethods;

	@Autowired
	TaskTransformService taskParseMethods;

	@Autowired
	Defaultresponse serverResponse;

	@GetMapping("/tasks")
	public ResponseEntity<?> getTasks() {
		List<TaskList> body = taskMethods.returnTaskList(taskMethods.getAllTasks());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@GetMapping("/tasks/project")
	public ResponseEntity<?> getTasksOfAProject(@RequestHeader("Authorization") String token, @RequestParam Long projectId) {
		
		if (token == null || token.isBlank()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		Project p = projectMethods.findProject(projectId);
		
		if (p == null) {
			return serverResponse.notfoundResponse("Proyecto no encontrado o no existente");
		}
		
		List<Task> l = taskMethods.getProjectActiveTasks(p);
		
		if (l.size() == 0) {
			return ResponseEntity.ok(l);
		}
		
		List<TaskSelectDto> dto = l.stream().map(t -> new TaskSelectDto(t)).collect(Collectors.toList());
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<?> addTask(@RequestHeader("Authorization") String token, @RequestBody TaskAdd t) {
		Map<String, String> response = new HashMap<>();

		try {

			Task newtask = taskMethods.add(t);

			TaskList body = taskParseMethods.mapTasksToTaskList(newtask);

			return ResponseEntity.ok(body);
		} catch (Exception e) {
			response.put("error", "500");
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PatchMapping("/tasks/{id}")
	public ResponseEntity<?> changeTaskStatus(@RequestHeader("Authorization") String token, @PathVariable Long id,
			@RequestBody Taskchangestatus newtask) {

		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token inválido");
		}

		Task t = taskMethods.getTaskById(id);

		if (t == null) {
			return serverResponse.notfoundResponse("Tarea no encontrada o inexistente");
		}

		int currentStatus = t.getStatus();
		int newStatus = newtask.getStatus();

		try {
			if (!taskMethods.isValidChange(currentStatus, newStatus)) {
				return serverResponse.badrequestResponse("Transición de estado no permitida");
			}

			t.setStatus(newStatus);
			TaskList response = taskMethods.returnTaskList(taskMethods.changeStatus(t));

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return serverResponse.badrequestResponse(e.getMessage());
		}

	}

}
