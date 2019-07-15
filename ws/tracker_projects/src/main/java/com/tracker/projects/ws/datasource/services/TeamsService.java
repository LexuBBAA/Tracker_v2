package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.TeamDto;

public interface TeamsService {
    TeamDto getTeamById(String teamId);
    TeamDto updateTeam(TeamDto updatedTeam);
    boolean deleteTeamById(String teamId);
    TeamDto createTeam(TeamDto teamDto);
}
