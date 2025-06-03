package com.tasktogether.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Comment;
import com.tasktogether.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentData;
	
	public Comment addComment(Comment c) {
		return commentData.save(c);
	}
	
}
