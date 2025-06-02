package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
