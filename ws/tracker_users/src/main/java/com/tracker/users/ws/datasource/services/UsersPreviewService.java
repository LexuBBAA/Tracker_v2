package com.tracker.users.ws.datasource.services;

import com.tracker.users.ws.datasource.dto.UserPreviewDto;

import java.util.List;

public interface UsersPreviewService {
	List<UserPreviewDto> findAll();
	UserPreviewDto findById(String userId);
	List<UserPreviewDto> findAllByUsername(String username);
	List<UserPreviewDto> findAllByCreatedById(String createdById);
}
