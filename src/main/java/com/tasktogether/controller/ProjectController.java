package com.tasktogether.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktogether.dto.ProjectAddDTO;
import com.tasktogether.dto.ProjectMinDTO;
import com.tasktogether.model.Project;
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
	
}
