package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import com.tracker.projects.ws.datasource.services.impl.SprintsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SprintsController {
    @Autowired
    private SprintsServiceImpl sprintsService;

    @GetMapping("/")
    public String getStatus() {
        return "Available";
    }

    @GetMapping("/{projectId}/sprints")
    public ResponseEntity getSprints(@PathVariable(value = "projectId") Integer projectId) {
        List<SprintDto> sprintDtos = sprintsService.getSprintsForProject(projectId);
        return new ResponseEntity<>(new HttpEntity<>(sprintDtos), HttpStatus.OK);
    }

    @PostMapping("/sprints")
    public ResponseEntity createSprint(@RequestBody SprintDto sprintDto) {
        SprintDto savedSprint = sprintsService.createSprint(sprintDto);
        if(savedSprint.id == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new HttpEntity<>(savedSprint), HttpStatus.OK);
    }

    @PutMapping("/sprint")
    public ResponseEntity updateSprint(
            @RequestBody SprintDto sprintDto
    ) {
        SprintDto savedSprint = sprintsService.updateSprint(sprintDto);
        return savedSprint == null ? new ResponseEntity(HttpStatus.NO_CONTENT): new ResponseEntity<>(new HttpEntity<>(savedSprint), HttpStatus.OK);
    }

    @DeleteMapping("/sprint/{sprintId}")
    public ResponseEntity deleteSprint(@PathVariable(value = "sprintId") Integer sprintId) {
        boolean deleted = sprintsService.deleteSprint(sprintId);
        return deleted ? new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
