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

	public Task getTaskById(Long id) {
		return taskData.findById(id).orElse(null);
	}

	public List<Task> getUserTasks(Long id) throws Exception {

		User u = userData.findById(id).orElse(null);

		if (u == null) {
			throw new Exception("Usuario no encontrado o no existente");
		} else {

			List<Task> tasks = taskData.findByUserCreator(u);

			return null;
		}

	}

	public Task add(TaskAdd t) throws Exception {

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

	public Task changeStatus(Task t) {
		return taskData.save(t);
	}

	public boolean isValidChange(int actualStatus, int newStatus) {
		switch (actualStatus) {
		case 0:
			return newStatus == 1; // pendiente → en progreso
		case 1:
			return newStatus == 2; // en progreso → completada
		case 2:
			return false; // completada → ningún otro estado permitido
		default:
			return false;
		}
	}

	/**
	 * Esta función llama a un servicio que transforma la lista de Tareas en un DTO
	 * de tareas
	 * 
	 * @param l lista de Tareas
	 * @return lista del DTO de tareas
	 */
	public List<TaskList> returnTaskList(List<Task> l) {
		return taskParseMethods.mapTasksToTaskList(l);
	}

	public TaskList returnTaskList(Task t) {
		return taskParseMethods.mapTasksToTaskList(t);
	}
}
