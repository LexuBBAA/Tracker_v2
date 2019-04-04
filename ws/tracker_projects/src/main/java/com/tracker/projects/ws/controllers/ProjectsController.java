package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectsController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}

	@GetMapping("/projects")
	public ResponseEntity getProjects() {
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity getProjectDetails(
			@PathVariable(value = "projectId") Integer projectId
	) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity createProject(@RequestBody ProjectDto projectDto) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity updateProject(
			@RequestBody ProjectDto projectDto
	) {
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
