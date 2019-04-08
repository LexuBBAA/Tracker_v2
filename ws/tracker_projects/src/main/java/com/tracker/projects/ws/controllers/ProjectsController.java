package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import com.tracker.projects.ws.datasource.dtos.ProjectPreviewDto;
import com.tracker.projects.ws.datasource.services.impl.ProjectsPreviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectsController {

	@Autowired
	private ProjectsPreviewServiceImpl previewService;

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}

	@GetMapping("/projects")
	public ResponseEntity getProjectPreviews() {
		List<ProjectPreviewDto> projectDtos = previewService.getProjects();
		HttpEntity<List<ProjectPreviewDto>> httpEntity = new HttpEntity<>(projectDtos);
		return new ResponseEntity<>(httpEntity, HttpStatus.OK);
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

	@DeleteMapping("/{projectId}")
	public ResponseEntity deleteProject(@PathVariable(value = "projectId") Integer projectId) {
		boolean deleted = previewService.deleteProject(projectId);
		return deleted ? new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}
