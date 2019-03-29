package com.tracker.users.ws.datasource.services.impl;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.entities.UserPreviewEntity;
import com.tracker.users.ws.datasource.repositories.UsersPreviewRepository;
import com.tracker.users.ws.datasource.services.UsersPreviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersPreviewServiceImpl implements UsersPreviewService {
    @Autowired
    private UsersPreviewRepository repository;

    @Override
    public UserDto getUserById(int userId) {
        Optional<UserPreviewEntity> optionalUserEntity = repository.findById(userId);
        return optionalUserEntity.map(this::getFromEntity).orElse(null);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<UserPreviewEntity> optionalUserEntity = repository.findByUsername(username);
        return optionalUserEntity.map(this::getFromEntity).orElse(null);
    }

    @NonNull
    private UserDto getFromEntity(@NonNull UserPreviewEntity entity) {
        return new UserDto(entity);
    }
}
