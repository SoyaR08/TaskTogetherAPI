package com.tasktogether.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectSimpleDTO;
import com.tasktogether.dto.project.ProjectHome;
import com.tasktogether.dto.task.TaskList;
import com.tasktogether.dto.user.UserMember;
import com.tasktogether.model.Member;
import com.tasktogether.model.Project;
import com.tasktogether.model.TaskUser;
import com.tasktogether.model.User;
import com.tasktogether.repository.ProjectRepository;
import com.tasktogether.repository.UserRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectData;

	@Autowired
	UserRepository userData;

//	@Autowired
//	HistoricalRepository historicalData;

	@Autowired
	UserService userMethods;

	public List<Project> getProjects() {
		return projectData.findAll();
	}

	public Project findProject(Long id) {
		return projectData.findById(id).orElse(null);
	}

	public User findOwner(String email) {
		List<User> user = userData.findByEmailLike(email);
		return user.size() > 0 ? user.get(0) : null;
	}

	public Page<Project> getInProgressUserProjects(User u, Pageable pageable) {
		return projectData.findByStatusAndUserCreator(0, u, pageable);
	}

	public Project addProject(ProjectAddDTO p) {
		User creator = userMethods.findUser(p.getUserCreator());
		Project prt = new Project(p, LocalDate.parse(p.getStart_date()), LocalDate.parse(p.getEnd_date()), creator);

		List<User> usermembers = p.getMembers().stream().map(u -> userMethods.findUser(u.getId()))
				.filter(Objects::nonNull) // Para objetos no nulos
				.collect(Collectors.toList());

		List<Member> members = usermembers.stream().map(mem -> new Member(prt, mem, "PROJECT_MEMBER"))
				.collect(Collectors.toList());

		prt.setMembers(members);

		return projectData.save(prt);
	}

	public Project finish(Project p) {
		return projectData.save(p);
	}

	public Project parseAddToProject(ProjectAddDTO p) {
		User u = userMethods.findUser(p.getUserCreator());
		Project prt = new Project(p, LocalDate.parse(p.getStart_date()), LocalDate.parse(p.getEnd_date()), u);
		return prt;
	}

	public List<ProjectSimpleDTO> parseToSimpleDTO(List<Project> lp) {

		List<ProjectSimpleDTO> simpleList = lp.stream().map(project -> new ProjectSimpleDTO(project)).toList();
		return simpleList;
	}

	public ProjectHome mapProjectToProjectHome(Project p) {

		List<TaskList> pending = new ArrayList<>();
		List<TaskList> progress = new ArrayList<>();
		List<TaskList> finished = new ArrayList<>();

		p.getTasks().forEach(t -> {
			List<MinUserInfo> members = mapMemberToUserMinDTO(t.getAssignedUsers());
			switch (t.getStatus()) {
			case 0:
				pending.add(new TaskList(t, members));
				break;
			case 1:
				progress.add(new TaskList(t, members));
				break;

			case 2:
				finished.add(new TaskList(t, members));
				break;

			}
		});
		
		pending.sort((t1, t2) -> t2.getPriority() - t1.getPriority()); //Ordeno para que las tareas urgentes sean las primeras
		progress.sort((t1, t2) -> t2.getPriority() - t1.getPriority());
		finished.sort((t1, t2) -> t2.getPriority() - t1.getPriority());

		List<UserMember> listDTO = p.getMembers().stream().map(user -> {
			return new UserMember(user);
		}).toList();

		return new ProjectHome(p, listDTO, pending, progress, finished);
	}

	private List<MinUserInfo> mapMemberToUserMinDTO(List<TaskUser> members) {
		return members.size() > 0
				? members.stream().map(member -> new MinUserInfo(member.getUser())).collect(Collectors.toList())
				: new ArrayList<MinUserInfo>();
	}

}
