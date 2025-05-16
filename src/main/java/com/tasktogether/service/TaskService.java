package com.tasktogether.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.dto.task.TaskAdd;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.model.Project;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;
import com.tasktogether.repository.ProjectRepository;
import com.tasktogether.repository.TaskRepository;
import com.tasktogether.repository.UserRepository;
import com.tasktogether.service.transform.TaskTransformService;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskData;
	
	@Autowired
	ProjectRepository projectData;
	
	@Autowired
	UserRepository userData;
	
	@Autowired
	TaskTransformService taskParseMethods;
	
	public List<Task> getAllTasks() {
		return taskData.findAll();
	}
	
	public List<Task> getUserTasks(Long id) throws Exception{
		
		User u = userData.findById(id).orElse(null);
		
		if (u == null) {
			throw new Exception("Usuario no encontrado o no existente");
		} else {
			
			List<Task> tasks = taskData.findByUserCreator(u);
			
			return null;
		}
		
	}
	
	public Task add(TaskAdd t) throws Exception{
		
		Project p = projectData.findById(t.getProjectId()).orElse(null);
		
		if (p == null || p.getStatus().equals("FINISHED")) {
			throw new Exception("Este proyecto no existe o ya ha finalizado");
		}
		
		LocalDate parsedDate = LocalDate.parse(t.getLimitDate());
		
		if (parsedDate.isBefore(p.getStart_date()) || parsedDate.isAfter(p.getEnd_date())) {
		    throw new Exception("La fecha límite de la tarea debe estar comprendida entre la duración del proyecto");
		}
		
		User u = userData.findById(t.getUserCreator()).orElse(null);
		
		if (u == null) {
			throw new Exception("Este usuario no existe");
		}
		
		Task newtask = taskData.save(new Task(t, p, u));
		
		return newtask;
	}
	
	public List<TaskList> returnTaskList(List<Task> l) {
		return taskParseMethods.mapTasksToTaskList(l);
	}
	
}
