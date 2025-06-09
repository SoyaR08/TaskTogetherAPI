package com.tasktogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Comment;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;
import com.tasktogether.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentData;
	
	public List<Comment> findTaskComments(Task t) {
		return commentData.findByTaskIdEquals(t);
	}
	
	public Comment addComment(Comment c) {
		return commentData.save(c);
	}
	
	public boolean isCommentOwner(Comment c, User u) {
		return c.getUserId().equals(u);
	}
	
}
