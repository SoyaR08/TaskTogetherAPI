package com.tasktogether.service.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.model.Task;
import com.tasktogether.model.TaskUser;

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
	
	public TaskList mapTasksToTaskList(Task t) {
		List<MinUserInfo> users = t.getAssignedUsers().size() > 0 ? 
				mapTaskUserToMinUserInfo(t.getAssignedUsers()) : new ArrayList<MinUserInfo>();
		return new TaskList(t, users);
	}
	
	private List<MinUserInfo> mapTaskUserToMinUserInfo(List<TaskUser> l) {
		return l.stream().map(assigned -> new MinUserInfo(assigned.getUser())).collect(Collectors.toList());
	}
	
	public List<Task> mapTaskUserToTask(List<TaskUser> tu) {
		return tu.stream().map(taskuser -> taskuser.getTask()).collect(Collectors.toList());
	}

}
