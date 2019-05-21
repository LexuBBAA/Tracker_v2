package com.tracker.auth.ws.datasources.services.impl;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.entities.TokenEntity;
import com.tracker.auth.ws.datasources.repositories.TokensRepository;
import com.tracker.auth.ws.datasources.services.TokensService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokensServiceImpl implements TokensService {
    @Autowired
    private TokensRepository repository;

    @Override
    public TokenDto findByTokenId(Long tokenId) {
        Optional<TokenEntity> tokenEntity = repository.findById(tokenId);
        return tokenEntity.map(TokenDto::new).orElse(null);
    }

    @Override
    public TokenDto generateToken() {
        TokenEntity newToken = new TokenEntity(generateUniqueToken(), generateRefreshToken());
        TokenEntity storedEntity = repository.save(newToken);
        if(storedEntity != null) {
            return new TokenDto(storedEntity);
        }
        return null;
    }

    @Override
    public TokenDto refreshToken(TokenDto token) {
        if(!repository.existsByToken(token.token)) {
            return null;
        }

        TokenEntity storedToken = repository.findByToken(token.token);
        if(!storedToken.getRefreshToken().equals(token.refreshToken)) {
            return null;
        }

        storedToken.setToken(generateUniqueToken());

        TokenEntity updatedToken = repository.save(storedToken);
        if(updatedToken != null) {
            return new TokenDto(updatedToken);
        }
        return null;
    }

    @Override
    public TokenDto getToken(String token) {
        TokenEntity storedEntity = repository.findByToken(token);
        if(storedEntity != null) {
            return new TokenDto(storedEntity);
        }

        return null;
    }

    @Override
    public TokenDto getByRefreshToken(String refreshToken) {
        TokenEntity storedToken = repository.findByRefreshToken(refreshToken);
        if(storedToken != null) {
            return new TokenDto(storedToken);
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        if(!repository.existsByToken(token)) {
            return false;
        }

        TokenEntity storedToken = repository.findByToken(token);
        return !storedToken.getExpiresAt().isBefore(LocalDateTime.now());
    }

    @Override
    public boolean deleteToken(String token) {
        if(repository.existsByToken(token)) {
            repository.deleteByToken(token);
            return true;
        }
        return false;
    }

    @NonNull
    private String generateUniqueToken() {
        String generatedToken = RandomStringUtils.randomAlphanumeric(100);
        if(repository.existsByToken(generatedToken)) {
            return generateUniqueToken();
        }

        return generatedToken;
    }

    @NonNull
    private String generateRefreshToken() {
        String generatedToken = RandomStringUtils.randomAlphanumeric(100);
        if(repository.existsByRefreshToken(generatedToken)) {
            return generateRefreshToken();
        }

        return generatedToken;
    }
}
