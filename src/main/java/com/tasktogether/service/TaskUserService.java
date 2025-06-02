package com.tasktogether.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Task;
import com.tasktogether.model.TaskUser;
import com.tasktogether.model.TaskUserID;
import com.tasktogether.model.User;
import com.tasktogether.repository.TaskRepository;
import com.tasktogether.repository.TaskUserRepository;
import com.tasktogether.repository.UserRepository;

@Service
public class TaskUserService {

	@Autowired
	TaskUserRepository taskuserData;
	
	@Autowired
	UserRepository userData;
	
	@Autowired
	TaskRepository taskData;
	
	public TaskUser assignTask(TaskUser t) {
		return taskuserData.save(t);
	}
	
	public User findUserbyId(Long id) {
		return userData.findById(id).orElse(null);
	}
	
	public Task findTaskbyId(Long id) {
		return taskData.findById(id).orElse(null);
	}
	
	public boolean existsAssignement(TaskUserID tu) {
		return taskuserData.existsById(tu);
	}
}
