package com.tracker.auth.ws.datasources.users.services;

import com.tracker.auth.ws.datasources.users.dto.UserDto;

public interface UsersService {
    UserDto authenticateUser(UserDto userDto);
}
