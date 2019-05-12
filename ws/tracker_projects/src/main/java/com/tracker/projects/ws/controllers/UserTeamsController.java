package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.UserTeamDto;
import com.tracker.projects.ws.datasource.services.UserTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTeamsController {
    @Autowired
    private UserTeamsService service;

    @GetMapping("/users2teams")
    public ResponseEntity getUsersToTeamsMapping(
            @Nullable @RequestParam(name = "userId", required = false) String userId,
            @Nullable @RequestParam(name = "teamId", required = false) String teamId
    ) {
        List<UserTeamDto> storedUserTeams = null;
        if(userId != null && teamId != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(userId != null) {
            storedUserTeams = service.getTeamsForUser(userId);
        }

        if(teamId != null) {
            storedUserTeams = service.getUsersForTeam(teamId);
        }

        if(storedUserTeams == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(storedUserTeams.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(storedUserTeams, HttpStatus.OK);
    }

    @PostMapping("/users2teams/{userId}")
    public ResponseEntity assignUserToTeam(
            @PathVariable(name = "userId") String userId,
            @RequestParam(name = "teamId") String teamId
    ) {
        UserTeamDto newUserTeam = new UserTeamDto(userId, teamId);
        UserTeamDto storedDto = service.assignUserToTeam(newUserTeam);
        if(storedDto == null) {
            return new ResponseEntity<>("User already assigned to team.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(storedDto, HttpStatus.OK);
    }

    @DeleteMapping("/users2teams")
    public ResponseEntity deleteUsersToTeamsMapping(
            @Nullable @RequestParam(name = "userId", required = false) String userId,
            @Nullable @RequestParam(name = "teamId", required = false) String teamId,
            @Nullable @RequestParam(name = "deleteAll", required = false, defaultValue = "false") boolean deleteAll
    ) {
        //  Either userId or teamId is required
        if(userId == null && teamId == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //  Check if only a specific mapping should be deleted
        if(userId != null && teamId != null) {
            if(service.removeUserFromTeam(new UserTeamDto(userId, teamId))) {
                //  Successfully deleted single mapping
                return new ResponseEntity(HttpStatus.OK);
            }

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        //  To delete all the mappings related to a User / Team,
        //  deleteAll flag must be set to <value>true</value>
        //  else the request will fail due to data integrity reasons
        if(!deleteAll) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //  Check if multiple mappings should be deleted based on the same User ID
        if(userId != null) {
            if(service.removeUserFromAllTeams(userId)) {
                //  User removed from all the Teams
                return new ResponseEntity(HttpStatus.OK);
            }

            //  User was not assigned to any Teams
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        //  Removing multiple mappings based on the same Team ID
        if(service.removeAllUsersFromTeam(teamId)) {
            //  All the Users removed from the Team
            return new ResponseEntity(HttpStatus.OK);
        }

        //  No Users were assigned to the Team
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
