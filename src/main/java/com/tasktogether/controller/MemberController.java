package com.tasktogether.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.ProjectListDto;
import com.tasktogether.dto.member.Membernew;
import com.tasktogether.dto.project.ProjectSelectDto;
import com.tasktogether.dto.task.TaskSelectDto;
import com.tasktogether.dto.user.UserMember;
import com.tasktogether.libraries.Defaultresponse;
import com.tasktogether.model.Member;
import com.tasktogether.model.Project;
import com.tasktogether.model.User;
import com.tasktogether.service.MemberService;
import com.tasktogether.service.ProjectService;
import com.tasktogether.service.UserService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberMethods;
	
	@Autowired
	ProjectService projectMethods;
	
	@Autowired
	UserService usuarioService;
	
	@Autowired
	Defaultresponse serverResponse;
	
	@GetMapping("/members")
	public ResponseEntity<?> projectsMembership(@RequestHeader("Authorization") String token, @RequestParam Long userId) {
		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		User u = usuarioService.findUser(userId);
		
		if (u == null) {
			return serverResponse.notfoundResponse("Usuario no encontrado o no existente");
		}
		
		List<Member> memberships = memberMethods.findMembershipsByUser(u);
		
		List<ProjectListDto> userProjects = memberships.stream()
				.map(membership -> new ProjectListDto(membership.getProject())).collect(Collectors.toList());
		
		return ResponseEntity.ok(userProjects);
		
	}
	
	@GetMapping("/members/list")
	public ResponseEntity<?> projectsMembershipSelect(@RequestHeader("Authorization") String token, @RequestParam Long userId) {
		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		User u = usuarioService.findUser(userId);
		
		if (u == null) {
			return serverResponse.notfoundResponse("Usuario no encontrado o no existente");
		}
		
		List<Member> memberships = memberMethods.findMembershipsByUser(u);
		
		List<ProjectSelectDto> userProjects = memberships.stream()
				.map(membership -> new ProjectSelectDto(membership.getProject())).collect(Collectors.toList());
		
		return ResponseEntity.ok(userProjects);
	}
	
	@GetMapping("/members/listmembers")
	public ResponseEntity<?> usersMembership(@RequestHeader("Authorization") String token, @RequestParam Long projectId) {
		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		Project p = projectMethods.findProject(projectId);
		
		if (p == null) {
			return serverResponse.notfoundResponse("Proyecto no encontrado o no existente");
		}
		
		List<UserMember> membersOf = p.getMembers().stream().map(m -> new UserMember(m)).collect(Collectors.toList());
		
		return ResponseEntity.ok(membersOf);
	}
	
	@GetMapping("/members/listtasks")
	public ResponseEntity<?> projectTasks(@RequestHeader("Authorization") String token, @RequestParam Long projectId) {
		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		Project p = projectMethods.findProject(projectId);
		
		if (p == null) {
			return serverResponse.notfoundResponse("Proyecto no encontrado o inexistente");
		}
		
		List<TaskSelectDto> tasks = p.getTasks().stream().map(t -> new TaskSelectDto(t)).collect(Collectors.toList());
		
		return ResponseEntity.ok(tasks);
	}
	
	@PostMapping("/members")
	public ResponseEntity<?> addmembersToProject(@RequestHeader("Authorization") String token, @RequestBody Membernew m) {
		
		if (token == null || token.isEmpty()) {
			return serverResponse.badrequestResponse("Token requerido");
		}
		
		Project p = projectMethods.findProject(m.getProjectId());
		
		if (p == null) {
			return serverResponse.notfoundResponse("Proyecto no encontrado o no existente");
		}
		
		List<Member> members = m.getMembers().stream()
				.map(minuser -> usuarioService.findUser(minuser.getId()))
				.filter(Objects::nonNull)
				.map(user -> new Member(p, user, "PROJECT_MEMBER")).collect(Collectors.toList());
		
		memberMethods.addMembers(members);
		
		return ResponseEntity.ok(Map.of("message", "miembros añadidos con éxito"));
	}
	
}
