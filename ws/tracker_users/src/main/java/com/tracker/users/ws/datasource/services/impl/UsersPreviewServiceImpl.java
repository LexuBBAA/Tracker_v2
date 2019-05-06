package com.tracker.users.ws.datasource.services.impl;

import com.tracker.users.ws.datasource.dto.UserPreviewDto;
import com.tracker.users.ws.datasource.entities.UserEntity;
import com.tracker.users.ws.datasource.repositories.UsersRepository;
import com.tracker.users.ws.datasource.services.UsersPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersPreviewServiceImpl implements UsersPreviewService {
    @Autowired
    private UsersRepository repository;

	@Override
	public UserPreviewDto findById(String userId) {
		UserEntity storedEntity = repository.findByUserId(userId);
		if(storedEntity != null) {
			return getFromEntity(storedEntity);
		} else {
			return null;
		}
	}

	@Override
	public List<UserPreviewDto> findAllByUsername(String username) {
		return getFromEntities(repository.findAllByUsernameLike(username));
	}
	
	@Override
	public List<UserPreviewDto> findAll() {
		return getFromEntities(repository.findAll());
	}
	
	@Override
	public List<UserPreviewDto> findAllByCreatedById(String createdById) {
		return getFromEntities(repository.findAllByCreatedBy(createdById));
	}

	private UserPreviewDto getFromEntity(UserEntity entity) {
		return new UserPreviewDto(entity);
	}
	
	private List<UserPreviewDto> getFromEntities(List<UserEntity> entities) {
		List<UserPreviewDto> dtos = new ArrayList<>();
		for(UserEntity entity: entities) {
			dtos.add(getFromEntity(entity));
		}
		return dtos;
	}
}
