package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.TeamDto;
import com.tracker.projects.ws.datasource.dtos.TeamPreviewDto;
import com.tracker.projects.ws.datasource.services.TeamsPreviewService;
import com.tracker.projects.ws.datasource.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private TeamsPreviewService teamsPreviewService;

    @GetMapping("/")
    public String getStatus() {
        return "Available";
    }

    @GetMapping("/teams")
    public ResponseEntity getTeams() {
        List<TeamPreviewDto> storedTeams = teamsPreviewService.getTeams();

        if(storedTeams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(storedTeams, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity getTeamDetails(@PathVariable(value = "teamId") String teamId) {
        TeamDto dto = teamsService.getTeamById(teamId);
        if(dto == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createTeam(@RequestBody TeamDto newTeam) {
        if(newTeam.createdBy == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newTeam.teamId != null) {
            newTeam.teamId = null;
        }

        TeamDto dto = teamsService.createTeam(newTeam);
        if(dto == null || dto.id == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateTeam(@RequestBody TeamDto team) {
        if(team.teamId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TeamDto dto = teamsService.updateTeam(team);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteTeam(@PathVariable(value = "teamId") String teamId) {
        if(teamsService.deleteTeamById(teamId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/createdby/{userId}")
    public ResponseEntity getTeamsCreatedBy(@PathVariable(value = "userId") String userId) {
        List<TeamPreviewDto> dtos = teamsPreviewService.getTeamsByCreatedById(userId);
        if(dtos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
