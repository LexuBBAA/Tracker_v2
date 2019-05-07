package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.TeamPreviewDto;

import java.util.List;

public interface TeamsPreviewService {
    List<TeamPreviewDto> getTeams();
    List<TeamPreviewDto> getTeamsByName(String name);
    List<TeamPreviewDto> getTeamsByCreatedById(String createdById);
}
