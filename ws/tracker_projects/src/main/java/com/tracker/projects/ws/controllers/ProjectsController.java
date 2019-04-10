package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import com.tracker.projects.ws.datasource.dtos.ProjectPreviewDto;
import com.tracker.projects.ws.datasource.services.ProjectsPreviewService;
import com.tracker.projects.ws.datasource.services.ProjectsService;
import com.tracker.projects.ws.datasource.services.impl.ProjectsPreviewServiceImpl;
import com.tracker.projects.ws.datasource.services.impl.ProjectsServiceImpl;
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

	@Autowired
	private ProjectsServiceImpl projectsService;

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
		ProjectDto projectDto = projectsService.getProjectById(projectId);
		return projectDto == null ? new ResponseEntity(HttpStatus.NO_CONTENT): new ResponseEntity<>(new HttpEntity<>(projectDto), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity createProject(@RequestBody ProjectDto projectDto) {
		ProjectDto savedProject = projectsService.createProject(projectDto);
		if(savedProject.id == null) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new HttpEntity<>(savedProject), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity updateProject(
			@RequestBody ProjectDto projectDto
	) {
		ProjectDto savedProject = projectsService.updateProject(projectDto);
		return savedProject == null ? new ResponseEntity(HttpStatus.NO_CONTENT): new ResponseEntity<>(new HttpEntity<>(savedProject), HttpStatus.OK);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity deleteProject(@PathVariable(value = "projectId") Integer projectId) {
		boolean deleted = previewService.deleteProject(projectId);
		return deleted ? new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}
