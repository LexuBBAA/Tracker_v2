package com.tracker.users.ws.datasource.services.impl;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.entities.UserEntity;
import com.tracker.users.ws.datasource.repositories.UsersRepository;
import com.tracker.users.ws.datasource.services.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<UserDto> getUsers() {
        return getFromEntities(repository.findAll());
    }

    @Override
    public UserDto getUserById(int userId) {
        Optional<UserEntity> optionalUserEntity = repository.findById(userId);
        return optionalUserEntity.map(this::getFromEntity).orElse(null);
    }

    @Override
    public UserDto updateUser(UserDto updatedUser) {
        return getFromEntity(repository.save(getFromDto(updatedUser)));
    }

    @Override
    public void deleteUserById(int userId) {
        repository.deleteById(userId);
    }

    @Override
    public UserDto createUser(UserDto user) {
        return getFromEntity(repository.save(getFromDto(user)));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = repository.findByUsername(username);
        return optionalUserEntity.map(this::getFromEntity).orElse(null);
    }


    @Override
    public List<UserDto> getUsersManagedBy(int managerId) {
        return getFromEntities(repository.findByReportingTo(managerId));
    }

    @Override
    public List<UserDto> getUsersManagedBy(Set<Integer> managerIds) {
        return getFromEntities(repository.findByReportingToIn(managerIds));
    }

    @Override
    public List<UserDto> getUsersOwnedBy(int createdBy) {
        return getFromEntities(repository.findByCreatedBy(createdBy));
    }

    @Override
    public List<UserDto> getUsersOwnedBy(Set<Integer> createdByIds) {
        return getFromEntities(repository.findByCreatedByIn(createdByIds));
    }


    @Override
    public List<UserDto> getUsersInTeam(int teamId) {
        return getFromEntities(repository.findByTeamId(teamId));
    }

    @Override
    public List<UserDto> getUsersInTeams(Set<Integer> teamIds) {
        return getFromEntities(repository.findByTeamIdIn(teamIds));
    }


    @Override
    public List<UserDto> getUsersWithEfficiency(double efficiency) {
        return getFromEntities(repository.findByEfficiency(efficiency));
    }

    @Override
    public List<UserDto> getUsersBelowEfficiency(double lessThanEfficiency) {
        return getFromEntities(repository.findByEfficiencyLessThanEqual(lessThanEfficiency));
    }

    @Override
    public List<UserDto> getUsersAboveEfficiency(double aboveEfficiency) {
        return getFromEntities(repository.findByEfficiencyGreaterThanEqual(aboveEfficiency));
    }

    @Override
    public List<UserDto> getUsersInEfficiencyRange(double above, double below) {
        return getFromEntities(repository.findByEfficiencyGreaterThanEqualAndEfficiencyLessThanEqual(above, below));
    }


    @NonNull
    private UserDto getFromEntity(@NonNull UserEntity entity) {
        return new UserDto(entity);
    }

    @NonNull
    private UserEntity getFromDto(@NonNull UserDto dto) {
        return new UserEntity(dto);
    }

    @NonNull
    private List<UserDto> getFromEntities(List<UserEntity> entities) {
        List<UserDto> dtos = new ArrayList<>();
        for(UserEntity e: entities) {
            dtos.add(getFromEntity(e));
        }
        return dtos;
    }
}
