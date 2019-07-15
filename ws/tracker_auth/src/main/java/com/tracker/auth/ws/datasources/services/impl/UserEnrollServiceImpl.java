package com.tracker.auth.ws.datasources.services.impl;

import com.tracker.auth.ws.datasources.dtos.users.UserEnrollDto;
import com.tracker.auth.ws.datasources.entities.UserEnrollEntity;
import com.tracker.auth.ws.datasources.repositories.UserEnrollRepository;
import com.tracker.auth.ws.datasources.services.UserEnrollService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserEnrollServiceImpl implements UserEnrollService {
    @Autowired
    private UserEnrollRepository repository;

    @Override
    public UserEnrollDto findByUsername(String input) {
        UserEnrollEntity storedUser = repository.findByUsername(input);

        if(storedUser != null) {
            UserEnrollDto userDto = new UserEnrollDto();
            userDto.userId = storedUser.getUserId();
            userDto.username = storedUser.getUsername();
            userDto.setPassword(storedUser.getPassword());
            return userDto;
        }
        return null;
    }

    @Override
    public UserEnrollDto findByEmail(String input) {
        UserEnrollEntity storedUser = repository.findByEmail(input);

        if(storedUser != null) {
            UserEnrollDto userDto = new UserEnrollDto();
            userDto.userId = storedUser.getUserId();
            userDto.email = storedUser.getEmail();
            userDto.setPassword(storedUser.getPassword());
            return userDto;
        }
        return null;
    }

    @Override
    public UserEnrollDto findByPhone(String input) {
        UserEnrollEntity storedUser = repository.findByPhone(input);

        if(storedUser != null) {
            UserEnrollDto userDto = new UserEnrollDto();
            userDto.userId = storedUser.getUserId();
            userDto.phone = storedUser.getPhone();
            userDto.setPassword(storedUser.getPassword());
            return userDto;
        }
        return null;
    }

    @Override
    public UserEnrollDto createUser(UserEnrollDto newUser) {
        UserEnrollEntity newUserEntity = new UserEnrollEntity();
        newUserEntity.setUserId(generateUserId());
        newUserEntity.setEmail(newUser.email);
        newUserEntity.setUsername(newUser.username);
        if(newUser.phone != null) newUserEntity.setPhone(newUser.phone);
        else newUserEntity.setPhone(UserEnrollDto.DEFAULT_PHONE);
        newUserEntity.setFirstName(UserEnrollDto.DEFAULT_FIRST_NAME);
        newUserEntity.setLastName(UserEnrollDto.DEFAULT_LAST_NAME);
        newUserEntity.setPassword(newUser.getPassword());

        UserEnrollEntity storedUser = repository.save(newUserEntity);
        if(storedUser != null) {
            newUser.userId = storedUser.getUserId();
            newUser.username = storedUser.getUsername();
            newUser.email = storedUser.getEmail();
            newUser.phone = storedUser.getPhone();
            newUser.setFirstName(storedUser.getFirstName());
            newUser.setLastName(storedUser.getLastName());
            newUser.setPassword(null);
            return newUser;
        }
        return null;
    }

    @Override
    public UserEnrollDto updatePassword(UserEnrollDto userEnrollDto) {
        UserEnrollEntity storedEntity = null;
        if(userEnrollDto.email != null && repository.existsByEmail(userEnrollDto.email)) {
            storedEntity = repository.findByEmail(userEnrollDto.email);
        }

        if(storedEntity == null && userEnrollDto.username != null && repository.existsByUsername(userEnrollDto.username)) {
            storedEntity = repository.findByUsername(userEnrollDto.username);
        }

        if(storedEntity == null && userEnrollDto.phone != null && repository.existsByPhone(userEnrollDto.phone)) {
            storedEntity = repository.findByPhone(userEnrollDto.phone);
        }

        if(storedEntity == null) {
            return null;
        }

        storedEntity.setPassword(userEnrollDto.getPassword());

        UserEnrollEntity updatedUser = repository.save(storedEntity);

        if(updatedUser != null) {
            return userEnrollDto;
        }
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    @NonNull
    private String generateUserId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(25);
        if(repository.existsByUserId(generatedId)) {
            return generateUserId();
        }
        return generatedId;
    }
}
