package com.tracker.users.ws.datasource.services;

import com.tracker.users.ws.datasource.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UsersPreviewService {
    UserDto getUserById(int userId);
    UserDto getUserByUsername(String username);
}
