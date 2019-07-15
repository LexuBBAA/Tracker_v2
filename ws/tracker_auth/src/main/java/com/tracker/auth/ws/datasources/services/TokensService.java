package com.tracker.auth.ws.datasources.services;

import com.tracker.auth.ws.datasources.dtos.TokenDto;

import javax.transaction.Transactional;

public interface TokensService {
    TokenDto findByTokenId(Long tokenId);

    TokenDto generateToken();
    TokenDto refreshToken(TokenDto token);
    TokenDto getToken(String token);
    TokenDto getByRefreshToken(String refreshToken);

    boolean validateToken(String token);
    boolean deleteToken(String token);
}
