package com.tracker.auth.ws.datasources.services.impl;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.entities.TokenEntity;
import com.tracker.auth.ws.datasources.repositories.TokensRepository;
import com.tracker.auth.ws.datasources.services.TokensService;
import com.tracker.auth.ws.datasources.users.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.UUID;

@Service
public class TokensServiceImpl implements TokensService {
    @SuppressWarnings("all")
    @Autowired
    private TokensRepository repository;

    @Override
    public TokenDto getToken(UserDto userDto) {
        TokenDto generatedToken = null;
        TokenEntity tokenEntity = repository.findByDeviceId(userDto.deviceId);
        if(tokenEntity != null) {
            generatedToken = new TokenDto();
            generatedToken.id = tokenEntity.getId();
            generatedToken.token = tokenEntity.getToken();
            generatedToken.createdAt = tokenEntity.getCreatedAt();
            generatedToken.expiresAt = tokenEntity.getExpiresAt();
            generatedToken.deviceId = tokenEntity.getDeviceId();
        }

        if(generatedToken == null) {
            try {
                generatedToken = generateToken(userDto);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if(!isValidToken(generatedToken)) {
            try {
                generatedToken = refreshToken(generatedToken);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return generatedToken;
    }

    @Override
    public boolean validateToken(String token) {
        return repository.existsByToken(token);
    }

    private TokenDto generateToken(@NonNull UserDto userDto) throws NoSuchAlgorithmException {
        String newToken = generateNewToken();

        TokenDto tokenDto = null;
        if(!newToken.isEmpty()) {
            if(repository.existsByToken(newToken)) {
                return generateToken(userDto);
            } else {
                tokenDto = saveNewToken(newToken, userDto.deviceId);
            }
        }
        return tokenDto;
    }

    private TokenDto refreshToken(@NonNull TokenDto tokenDto) throws NoSuchAlgorithmException {
        String newToken = generateNewToken();

        TokenDto newTokenDto = null;
        if(!newToken.isEmpty()) {
            if(repository.existsByToken(newToken)) {
                return refreshToken(tokenDto);
            } else {
                newTokenDto = saveNewToken(newToken, tokenDto.deviceId);
            }
        }
        return newTokenDto;
    }

    private boolean isValidToken(@NonNull TokenDto tokenDto) {
        return tokenDto.createdAt.before(tokenDto.expiresAt) &&
                tokenDto.expiresAt.after(new Date(System.currentTimeMillis()));
    }

    private String generateNewToken() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    private TokenDto saveNewToken(@NonNull String newToken, @NonNull String deviceId) {
        TokenEntity savedToken = repository.save(new TokenEntity(newToken, deviceId));
        TokenDto tokenDto = new TokenDto();
        tokenDto.id = savedToken.getId();
        tokenDto.token = savedToken.getToken();
        tokenDto.deviceId = savedToken.getDeviceId();
        tokenDto.createdAt = savedToken.getCreatedAt();
        tokenDto.expiresAt = savedToken.getExpiresAt();
        return tokenDto;
    }

    private String bytesToHex(MessageDigest digest) {
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
