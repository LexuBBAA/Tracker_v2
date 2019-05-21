package com.tracker.auth.ws.datasources.services.impl;

import com.tracker.auth.ws.datasources.dtos.TokenDto;
import com.tracker.auth.ws.datasources.dtos.UserTokenDto;
import com.tracker.auth.ws.datasources.entities.UserTokenEntity;
import com.tracker.auth.ws.datasources.repositories.UserTokensRepository;
import com.tracker.auth.ws.datasources.services.UserTokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokensServiceImpl implements UserTokensService {
    @Autowired
    private UserTokensRepository repository;

    @Override
    public UserTokenDto createUserToken(String userId, TokenDto token) {
        if(repository.existsByUserId(userId) || repository.existsByTokenId(token.id)) {
            return null;
        }

        UserTokenEntity newUserToken = new UserTokenEntity();
        newUserToken.setUserId(userId);
        newUserToken.setTokenId(token.id);

        UserTokenEntity storedUserToken = repository.save(newUserToken);
        if(storedUserToken != null) {
            return new UserTokenDto(storedUserToken);
        }
        return null;
    }

    @Override
    public UserTokenDto findByUserId(String userId) {
        UserTokenEntity userToken = repository.findByUserId(userId);
        if(userToken != null) {
            return new UserTokenDto(userToken);
        }
        return null;
    }

    @Override
    public boolean deleteUserToken(String userId) {
        if(repository.existsByUserId(userId)) {
            repository.deleteByUserId(userId);
            return true;
        }
        return false;
    }
}
