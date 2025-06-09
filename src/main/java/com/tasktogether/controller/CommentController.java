package com.tasktogether.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.comment.Commentadd;
import com.tasktogether.dto.comment.CommentList;
import com.tasktogether.libraries.Defaultresponse;
import com.tasktogether.model.Comment;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;
import com.tasktogether.security.TokenUtils;
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

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/comments")
	public ResponseEntity<?> findTaskComments(@RequestHeader("Authorization") String token, @RequestParam Long taskId) {

		if (token == null || token.isBlank()) {
			return serverResponse.badrequestResponse("Token inválido");
		}

		Task t = taskMethods.getTaskById(taskId);

		if (t == null) {
			return serverResponse.notfoundResponse("Tarea no encontrada o inexistente");
		}

		List<Comment> commentList = commentMethods.findTaskComments(t);

		// Decodifico el token para asegurarme de que
		UsernamePasswordAuthenticationToken auth = TokenUtils.decodeToken(token);
		String principal = auth.getName(); // Me va a dar el email

		User u = usuarioService.findUserByUsername(principal);

		if (commentList.size() == 0) {
			return ResponseEntity.ok(new ArrayList<CommentList>());
		}

		List<CommentList> l = commentList.stream()
				.map(comment -> new CommentList(comment, commentMethods.isCommentOwner(comment, u)))
				.collect(Collectors.toList());

		return ResponseEntity.ok(l);
	}

	@PostMapping("/comments")
	public ResponseEntity<?> addComment(@RequestHeader("Authorization") String token, @RequestBody Commentadd c) {

		if (token == null || token.isBlank()) {
			return serverResponse.badrequestResponse("Token inválido");
		}

		User u = usuarioService.findUser(c.getUserId());

		if (u == null) {
			return serverResponse.notfoundResponse("Usuario no encontrado o inexistente");
		}

		// Decodifico el token para asegurarme de que
		UsernamePasswordAuthenticationToken auth = TokenUtils.decodeToken(token);
		String principal = auth.getName(); // Me va a dar el email

		User userToken = usuarioService.findUserByUsername(principal);

		Task t = taskMethods.getTaskById(c.getTaskId());

		if (t == null) {
			return serverResponse.notfoundResponse("Tarea no encontrada o inexistente");
		}
		
		Comment cmt = commentMethods.addComment(new Comment(c, u, t));
		
		CommentList cl = new CommentList(cmt, commentMethods.isCommentOwner(cmt, userToken));
		return ResponseEntity.ok(cl);
	}

}
