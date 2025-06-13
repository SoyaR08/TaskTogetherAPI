package com.tasktogether.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Historical;
import com.tasktogether.model.Project;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;
import com.tasktogether.repository.HistoricalRepository;

@Service
public class HistoricalService {

	@Autowired
	HistoricalRepository historicalData;
	
	public Historical addChange(Project prt, User u) {
		Historical h = new Historical(prt, u, u.getEmail()+" creó el proyecto "+prt.getName(), LocalDateTime.now());
		return historicalData.save(h);
	}
	
	public Historical addNewTask(Task t) {
		Historical h = new Historical(t.getProject(), t.getUserCreator(), t.getUserCreator().getEmail()+" creó la tarea "+t.getName(), LocalDateTime.now());
		return historicalData.save(h);
	}
	
	public List<Project> getRecentProjects(Long userId) {
		PageRequest limit = PageRequest.of(0, 3);
		return historicalData.findTop3RecentProjectsByUserId(userId, limit);
	}
}
