package com.tracker.auth.ws.datasources.services;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.users.dto.UserDto;

public interface TokensService {
    TokenDto getToken(UserDto userDto);
    boolean validateToken(String token);
}
