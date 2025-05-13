package com.tasktogether.service.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tasktogether.dto.task.TaskList;
import com.tasktogether.model.Task;

@Service
public class TaskTransformService {

	public List<TaskList> mapTasksToTaskList(List<Task> l) {
		List<TaskList> parsed = l.stream().map(t -> new TaskList(t)).collect(Collectors.toList());
		return parsed;
	}
	
}
