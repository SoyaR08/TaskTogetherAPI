package com.tasktogether.service.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.model.Task;

@Service
public class TaskTransformService {

	public List<TaskList> mapTasksToTaskList(List<Task> l) {
		List<TaskList> parsed = l.stream().map(t -> {
			List<MinUserInfo> users = t.getAssignedUsers()
					.stream().map(u -> new MinUserInfo(u.getUser())).collect(Collectors.toList());
			return new TaskList(t, users);
		}).collect(Collectors.toList());
		return parsed;
	}

}
