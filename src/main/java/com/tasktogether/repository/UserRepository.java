package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tasktogether.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	//@Query("SELECT u FROM Usuario WHERE u.email LIKE :email")
	List<User> findByEmailLike(String email);
	
}
