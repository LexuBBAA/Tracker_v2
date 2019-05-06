package com.tracker.users.ws.datasource.services.impl;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.entities.UserEntity;
import com.tracker.users.ws.datasource.repositories.UsersRepository;
import com.tracker.users.ws.datasource.services.UsersService;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<UserDto> getUsers() {
        return getFromEntities(repository.findAll());
    }
    
    @Override
	public UserDto getUserById(String userId) {
		return getFromEntity(repository.findByUserId(userId));
	}
	
	@Override
	public UserDto updateUser(UserDto updatedUser) {
		if(!repository.existsByUserId(updatedUser.userId)) {
			return null;
		}
		
		UserEntity storedEntity = repository.save(getFromDto(updatedUser));
		if(storedEntity == null) {
			return null;
		}
		
		return getFromEntity(storedEntity);
	}

	@Override
	public boolean deleteUserById(String userId) {
		if(repository.existsByUserId(userId)) {
			repository.deleteByUserId(userId);
			return true;
		}
		
		return false;
	}

	@Override
	public UserDto createUser(UserDto user) {
		user.userId = generateUserId();
		
		UserEntity storedEntity = repository.save(getFromDto(user));
		if(storedEntity == null) {
			return null;
		}
		
		return getFromEntity(storedEntity);
	}

	@Override
	public List<UserDto> getUsersByUsername(String username) {
		List<UserEntity> storedEntities = repository.findAllByUsernameLike(username);
		return getFromEntities(storedEntities);
	}
    


    @NonNull
    private UserDto getFromEntity(@NonNull UserEntity entity) {
        return new UserDto(entity);
    }

    @NonNull
    private UserEntity getFromDto(@NonNull UserDto dto) {
        return new UserEntity(dto);
    }

    @NonNull
    private List<UserDto> getFromEntities(List<UserEntity> entities) {
        List<UserDto> dtos = new ArrayList<>();
        for(UserEntity e: entities) {
            dtos.add(getFromEntity(e));
        }
        return dtos;
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
