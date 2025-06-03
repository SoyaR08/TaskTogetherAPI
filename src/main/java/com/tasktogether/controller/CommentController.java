package com.tasktogether.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.comment.Commentadd;
import com.tasktogether.dto.comment.Commentlist;
import com.tasktogether.libraries.Defaultresponse;
import com.tasktogether.model.Comment;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;
import com.tasktogether.service.CommentService;
import com.tasktogether.service.TaskService;
import com.tasktogether.service.UserService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentMethods;
	
	@Autowired
	Defaultresponse serverResponse;
	
	@Autowired
	UserService usuarioService;
	
	@Autowired
	TaskService taskMethods;
	
	@PostMapping("/comments")
	public ResponseEntity<?> addComment(@RequestHeader("Authorization") String token, @RequestBody Commentadd c) {
		
		if (token == null || token.isBlank()) {
			return serverResponse.badrequestResponse("Token inválido");
		}
		
		User u = usuarioService.findUser(c.getUserId());
		
		if (u == null ) {
			return serverResponse.notfoundResponse("Usuario no encontrado o inexistente");
		}
		
		Task t = taskMethods.getTaskById(c.getTaskId());
		
		if (t == null) {
			return serverResponse.notfoundResponse("Tarea no encontrada o inexistente");
		}
		Commentlist cl = new Commentlist(commentMethods.addComment(new Comment(c, u, t)));
		return ResponseEntity.ok(cl);
	}
	
}
