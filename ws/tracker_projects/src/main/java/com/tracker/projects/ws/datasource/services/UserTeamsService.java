package com.tracker.projects.ws.datasource.services;

import com.tracker.projects.ws.datasource.dtos.UserTeamDto;

import java.time.LocalDate;
import java.util.List;

public interface UserTeamsService {
    UserTeamDto assignUserToTeam(UserTeamDto userTeamDto);
    List<UserTeamDto> getTeamsForUser(String userId);
    List<UserTeamDto> getUsersForTeam(String teamId);

    //TODO: implement API endpoint for these...
    List<UserTeamDto> getUsersForTeamByJoinDate(String teamId, LocalDate joinDate);
    List<UserTeamDto> getUsersForTeamBeforeDate(String teamId, LocalDate beforeJoinDate);
    List<UserTeamDto> getUsersForTeamAfterDate(String teamId, LocalDate afterJoinDate);
    List<UserTeamDto> getUsersForTeamBetween(String teamId, LocalDate startDate, LocalDate endDate);

    boolean removeUserFromTeam(UserTeamDto userTeamDto);
    boolean removeUserFromAllTeams(String userId);
    boolean removeAllUsersFromTeam(String teamId);
}
