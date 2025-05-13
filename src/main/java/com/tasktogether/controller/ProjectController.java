package com.tasktogether.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectEditDTO;
import com.tasktogether.dto.ProjectListDTO;
import com.tasktogether.dto.ProjectMinDTO;
import com.tasktogether.model.Project;
import com.tasktogether.model.User;
import com.tasktogether.security.TokenUtils;
import com.tasktogether.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Proyectos", description = "Operaciones relacionadas con proyectos")
public class ProjectController {

	@Autowired
	ProjectService projectMethods;

	@GetMapping("/projects")
	public ResponseEntity<?> listProjects(@RequestHeader("Authorization") String token,
			@RequestParam(defaultValue = "1", required = false) int pageNumber) {
		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}

		try {
			UsernamePasswordAuthenticationToken auth = TokenUtils.decodeToken(token);
			String principal = auth.getName(); // Me devolverá el email

			User u = projectMethods.findOwner(principal);

			if (u == null) {
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "404");
				body.put("message", "No se ha encontrado al usuario con el email dado");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
			}
			Pageable pageable;
			if (pageNumber < 1) {
				pageable = PageRequest.of(0, 9);
			} else {

				pageable = PageRequest.of(pageNumber - 1, 9);
			}

			Page<Project> projects = projectMethods.getInProgressUserProjects(u, pageable);

			Page<ProjectListDTO> projectsDTO = projects.map(project -> {
				ProjectListDTO pldto = new ProjectListDTO(project);
				return pldto;
			});

			return ResponseEntity.status(HttpStatus.OK).body(projectsDTO);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "500");
			body.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}

	}

	@PostMapping("/projects/add")
	@Operation(summary = "Crear un proyecto", description = "Devuelve un usuario basado en su ID")
	@ApiResponses({
		    
		    @ApiResponse(responseCode = "200", description = "Proyecto creado")
		 })
	public ResponseEntity<?> addProyect(
			@Parameter(description = "Credenciales de crear proyecto", required = true) @RequestBody ProjectAddDTO pdto) {
		
		Project p = projectMethods.add(pdto);
		
		ProjectMinDTO pmdto = new ProjectMinDTO(p);
		
		return ResponseEntity.ok(pmdto);
		
	}

	@PatchMapping("/projects/{id}")
	public ResponseEntity<?> finishProject(@RequestHeader("Authorization") String token, @PathVariable Long id,
			@RequestBody ProjectEditDTO p) {

		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}

		try {

			Project prj = projectMethods.findProject(id);

			if (prj == null) {
				throw new Exception("No existe tal proyecto");
			}

			prj.setStatus(p.getStatus());

			ProjectMinDTO pmdto = new ProjectMinDTO(projectMethods.finish(prj));

			return ResponseEntity.ok(pmdto);
		} catch (Exception e) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "500");
			body.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}

	}

}
