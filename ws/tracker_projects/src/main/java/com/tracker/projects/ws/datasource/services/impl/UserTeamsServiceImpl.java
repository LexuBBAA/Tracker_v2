package com.tracker.projects.ws.datasource.services.impl;

import com.tracker.projects.ws.datasource.dtos.UserTeamDto;
import com.tracker.projects.ws.datasource.entities.UserTeamEntity;
import com.tracker.projects.ws.datasource.repositories.UserTeamsRepository;
import com.tracker.projects.ws.datasource.services.UserTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTeamsServiceImpl implements UserTeamsService {
    @Autowired
    private UserTeamsRepository repository;

    @Override
    public UserTeamDto assignUserToTeam(UserTeamDto userTeamDto) {
        if(repository.existsByUserIdAndTeamId(userTeamDto.userId, userTeamDto.teamId)) {
            return null;
        }

        UserTeamEntity storedEntity = repository.save(new UserTeamEntity(userTeamDto));
        return new UserTeamDto(storedEntity);
    }

    @Override
    public List<UserTeamDto> getTeamsForUser(String userId) {
        List<UserTeamEntity> storedEntities = repository.findAllByUserId(userId);
        List<UserTeamDto> dtos = new ArrayList<>();
        for(UserTeamEntity entity: storedEntities) {
            dtos.add(new UserTeamDto(entity));
        }
        return dtos;
    }

    @Override
    public List<UserTeamDto> getUsersForTeam(String teamId) {
        List<UserTeamEntity> storedEntities = repository.findAllByTeamId(teamId);
        return getFromEntities(storedEntities);
    }

    @Override
    public List<UserTeamDto> getUsersForTeamByJoinDate(String teamId, LocalDate joinDate) {
        List<UserTeamEntity> storedEntities = repository.findAllByTeamIdAndJoinedDate(teamId, joinDate);
        return getFromEntities(storedEntities);
    }

    @Override
    public List<UserTeamDto> getUsersForTeamBeforeDate(String teamId, LocalDate beforeJoinDate) {
        List<UserTeamEntity> storedEntities = repository.findAllByTeamIdAndJoinedDateBefore(teamId, beforeJoinDate);
        return getFromEntities(storedEntities);
    }

    @Override
    public List<UserTeamDto> getUsersForTeamAfterDate(String teamId, LocalDate afterJoinDate) {
        List<UserTeamEntity> storedEntities = repository.findAllByTeamIdAndJoinedDateAfter(teamId, afterJoinDate);
        return getFromEntities(storedEntities);
    }

    @Override
    public List<UserTeamDto> getUsersForTeamBetween(String teamId, LocalDate startDate, LocalDate endDate) {
        List<UserTeamEntity> storedEntities = repository.findAllByTeamIdAndJoinedDateAfterAndJoinedDateBefore(teamId, startDate, endDate);
        return getFromEntities(storedEntities);
    }

    @Override
    public boolean removeUserFromTeam(UserTeamDto userTeamDto) {
        if(repository.existsByUserIdAndTeamId(userTeamDto.userId, userTeamDto.teamId)) {
            repository.deleteByUserIdAndTeamId(userTeamDto.userId, userTeamDto.teamId);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUserFromAllTeams(String userId) {
        if(repository.existsByUserId(userId)) {
            repository.deleteByUserId(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAllUsersFromTeam(String teamId) {
        if(repository.existsByTeamId(teamId)) {
            repository.deleteByTeamId(teamId);
            return true;
        }
        return false;
    }

    @NonNull
    private List<UserTeamDto> getFromEntities(@NonNull List<UserTeamEntity> entities) {
        List<UserTeamDto> dtos = new ArrayList<>();
        for(UserTeamEntity entity: entities) {
            dtos.add(new UserTeamDto(entity));
        }
        return dtos;
    }
}
