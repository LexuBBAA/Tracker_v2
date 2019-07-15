package com.tracker.users.ws.datasource.services;

import com.tracker.users.ws.datasource.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getUsers();
    UserDto getUserById(String userId);
    UserDto updateUser(UserDto updatedUser);
    boolean deleteUserById(String userId);
    UserDto createUser(UserDto user);
    List<UserDto> getUsersByUsername(String username);
}
