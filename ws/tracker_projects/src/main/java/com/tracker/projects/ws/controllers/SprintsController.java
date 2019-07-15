package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.dtos.sprints.SprintPreviewDto;
import com.tracker.projects.ws.datasource.dtos.sprints.SprintStatusDto;
import com.tracker.projects.ws.datasource.services.sprints.SprintStatusService;
import com.tracker.projects.ws.datasource.services.sprints.SprintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SprintsController {
    @Autowired
    private SprintsService sprintsService;
    @Autowired
    private SprintStatusService sprintStatusService;

    @GetMapping("/sprints/{projectId}")
    public ResponseEntity getSprints(@PathVariable(name = "projectId") String projectId) {
        List<SprintPreviewDto> sprintPreviews = sprintsService.getSprintsForProject(projectId);
        if(sprintPreviews.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sprintPreviews, HttpStatus.OK);
    }

    @GetMapping("/sprints/details/{sprintId}")
    public ResponseEntity getSprintDetails(@PathVariable(name = "sprintId") String sprintId) {
        SprintDto sprintDetails = sprintsService.getSprintDetails(sprintId);
        if(sprintDetails == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sprintDetails, HttpStatus.OK);
    }

    @PostMapping("/sprints")
    public ResponseEntity createNewSprint(
            @RequestHeader(name = "userId") String userId,
            @RequestBody SprintDto newSprint
    ) {
        if(newSprint.title == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newSprint.startDate == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newSprint.endDate == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newSprint.project == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newSprint.status == null || newSprint.status.value == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        newSprint.createdBy = userId;
        SprintDto storedSprint = sprintsService.create(newSprint);
        if(storedSprint != null) {
            return new ResponseEntity<>(storedSprint, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/sprints")
    public ResponseEntity updateSprint(@RequestBody SprintDto sprint) {
        if(sprint.sprintId == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        SprintDto storedSprint = sprintsService.getSprintDetails(sprint.sprintId);
        if(storedSprint == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(sprint.title != null) {
            storedSprint.title = sprint.title;
        }

        if(sprint.startDate != null) {
            storedSprint.startDate = sprint.startDate;
        }

        if(sprint.endDate != null) {
            storedSprint.endDate = sprint.endDate;
        }

        if(sprint.project != null) {
            storedSprint.project = sprint.project;
        }

        if(sprint.status != null && sprint.status.value != null) {
            storedSprint.status = sprintStatusService.getByValue(sprint.status.value);
        }

        if(sprint.description != null) {
            storedSprint.description = sprint.description;
        }

        SprintDto updatedSprint = sprintsService.updateSprint(storedSprint);
        if(updatedSprint != null) {
            return new ResponseEntity<>(updatedSprint, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/sprints/{sprintId}")
    public ResponseEntity deleteSprint(@PathVariable(name = "sprintId") String sprintId) {
        if(sprintsService.deleteSprint(sprintId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
