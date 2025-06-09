package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Comment;
import com.tasktogether.model.Task;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByTaskIdEquals(Task taskId);
	
}
