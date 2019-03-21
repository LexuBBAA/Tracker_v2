package com.tracker.roles.ws.services;

import java.util.List;

import com.tracker.roles.ws.dtos.RoleDto;

public interface RolesService {

	List<RoleDto> getRoles();
	
	RoleDto getRole(RoleDto roleDto);
	
}
