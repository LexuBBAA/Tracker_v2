package com.tracker.auth.ws.datasources.services;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.dtos.UserTokenDto;

public interface UserTokensService {
    UserTokenDto createUserToken(String userId, TokenDto token);
    UserTokenDto findByUserId(String userId);
    boolean deleteUserToken(String userId);
}
