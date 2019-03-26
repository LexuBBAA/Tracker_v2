package com.tracker.auth.ws.datasources.users.services.impl;

import com.tracker.auth.ws.datasources.users.dto.UserDto;
import com.tracker.auth.ws.datasources.users.entities.UserEntity;
import com.tracker.auth.ws.datasources.users.repo.UsersRepository;
import com.tracker.auth.ws.datasources.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersRepository repository;

    @Nullable
    @Override
    public UserDto authenticateUser(@NonNull UserDto userDto) {
        UserDto retrievedUserDto = null;

        UserEntity userEntity = repository.findByUsername(userDto.username);
        if(userEntity.getPassword().contentEquals(userDto.password)) {
            retrievedUserDto = new UserDto();
            retrievedUserDto.userId = userEntity.getUserId();
            retrievedUserDto.username = userEntity.getUsername();
            retrievedUserDto.email = userEntity.getEmail();
            retrievedUserDto.phoneNo = userEntity.getPhoneNo();
        }

        return retrievedUserDto;
    }
}
