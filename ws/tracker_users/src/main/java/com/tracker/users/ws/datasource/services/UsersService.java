package com.tracker.users.ws.datasource.services;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.repositories.UsersPreviewRepository;

import java.util.List;
import java.util.Set;

public interface UsersService extends UsersPreviewService {
    List<UserDto> getUsers();
    UserDto getUserById(int userId);
    UserDto updateUser(UserDto updatedUser);
    void deleteUserById(int userId);
    UserDto createUser(UserDto user);
    UserDto getUserByUsername(String username);

    List<UserDto> getUsersManagedBy(int managerId);
    List<UserDto> getUsersManagedBy(Set<Integer> managerIds);

    List<UserDto> getUsersOwnedBy(int createdBy);
    List<UserDto> getUsersOwnedBy(Set<Integer> createdByIds);

    List<UserDto> getUsersInTeam(int teamId);
    List<UserDto> getUsersInTeams(Set<Integer> teamIds);

    List<UserDto> getUsersWithEfficiency(double efficiency);
    List<UserDto> getUsersBelowEfficiency(double lessThanEfficiency);
    List<UserDto> getUsersAboveEfficiency(double aboveEfficiency);
    List<UserDto> getUsersInEfficiencyRange(double above, double below);
}
