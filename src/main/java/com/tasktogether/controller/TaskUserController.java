package com.tasktogether.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.taskuser.Taskuseradd;
import com.tasktogether.libraries.Defaultresponse;
import com.tasktogether.model.Task;
import com.tasktogether.model.TaskUser;
import com.tasktogether.model.TaskUserID;
import com.tasktogether.model.User;
import com.tasktogether.service.TaskUserService;

import io.jsonwebtoken.MalformedJwtException;

@RestController
public class TaskUserController {

	@Autowired
	TaskUserService taskuserMethods;
	
	@Autowired
	Defaultresponse serverResponse;
	
	@PostMapping("/assigntask")
	public ResponseEntity<?> assignTaskToUser(@RequestHeader("Authorization") String token, 
			@RequestBody Taskuseradd assignement) {
		
		try {
			
			if (token == null || token.isEmpty()) {
				return serverResponse.forbiddenResponse("Token inválido");
			}
			
			if (assignement.getTaskId() == null) {
				return serverResponse.badrequestResponse("Debe proporcionar un id de tarea");
			}
			
			Task t = taskuserMethods.findTaskbyId(assignement.getTaskId());
			
			if (t == null) {
				return serverResponse.notfoundResponse("No existe una tarea con ese id");
			}
			
			if (t.getStatus() == 2) {
				return serverResponse.badrequestResponse("No se le pueden añadir miembros a una tarea finalizada");
			}
			
			User u = taskuserMethods.findUserbyId(assignement.getUserId());
			
			if (u == null) {
				return serverResponse.notfoundResponse("No existe un usuario con ese id");
			}
			
			if (taskuserMethods.existsAssignement(new TaskUserID(u.getId(), t.getId()))) {
				return serverResponse.badrequestResponse("Usuario ya asignado a tarea");
			}
			
			TaskUser tu = new TaskUser(u, t);
			
			taskuserMethods.assignTask(tu);
	
			
			return ResponseEntity.ok(Map.of("message", "Tarea asignada a usuario con éxito"));
		}catch(MalformedJwtException jwte) {
			return serverResponse.servererrorResponse(jwte.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return serverResponse.servererrorResponse(e.getMessage());
		}
		
	}
	
}
