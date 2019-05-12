package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import com.tracker.projects.ws.datasource.dtos.sprints.SprintPreviewDto;
import com.tracker.projects.ws.datasource.services.ProjectsService;
import com.tracker.projects.ws.datasource.services.sprints.SprintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private SprintsService sprintsService;

    @GetMapping("/projects")
    public ResponseEntity getProjects() {
        List<ProjectDto> projects = projectsService.getAll();
        if (projects.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity createProject(
            @RequestHeader(value = "userId") String userId,
            @RequestBody ProjectDto newProject
    ) {
        if (newProject.title == null) {
            new Exception(ProjectsController.class.getSimpleName() + "- createProject : mandatory fields missing : title: " + newProject.toString()).printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        newProject.createdBy = userId;
        ProjectDto storedProject = projectsService.create(newProject);
        if(storedProject != null) {
            return new ResponseEntity<>(storedProject, HttpStatus.OK);
        }
        new Exception(ProjectsController.class.getSimpleName() + "- createProject : failed to create task: " + newProject.toString()).printStackTrace();
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/projects")
    public ResponseEntity updateProject(@RequestBody ProjectDto project) {
        if (project.projectId == null || project.title == null) {
            new Exception(ProjectsController.class.getSimpleName() + "- updateProject : mandatory fields missing (projectId or title): " + project.toString()).printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ProjectDto storedProject = projectsService.getById(project.projectId);
        if(storedProject == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        storedProject.title = project.title;
        storedProject.description = project.description;
        storedProject.activeSprint = project.activeSprint;
        storedProject.assignedTeam = project.assignedTeam;

        ProjectDto updatedProject = projectsService.update(storedProject);
        if (updatedProject != null) {
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        }

        new Exception(ProjectsController.class.getSimpleName() + "- updateProject : failed to update project: " + project.toString()).printStackTrace();
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity deleteProject(@PathVariable(name = "projectId") String projectId) {
        if(projectsService.deleteById(projectId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
