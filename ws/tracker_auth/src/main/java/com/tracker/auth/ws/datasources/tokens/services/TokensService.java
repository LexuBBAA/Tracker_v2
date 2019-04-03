package com.tracker.auth.ws.datasources.tokens.services;

import com.tracker.auth.ws.datasources.tokens.dto.TokenDto;
import com.tracker.auth.ws.datasources.users.dto.UserDto;

public interface TokensService {
    TokenDto getToken(UserDto userDto);
    boolean validateToken(String token);
}
