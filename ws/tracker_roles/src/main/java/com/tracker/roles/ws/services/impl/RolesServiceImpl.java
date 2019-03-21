package com.tracker.roles.ws.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.roles.ws.daos.RolesDao;
import com.tracker.roles.ws.dtos.RoleDto;
import com.tracker.roles.ws.entities.RoleEntity;
import com.tracker.roles.ws.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;
	
	@Override
	public List<RoleDto> getRoles() {
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		for(RoleEntity entity: rolesDao.findAll()) {
			RoleDto roleDto = new RoleDto();
			BeanUtils.copyProperties(entity, roleDto);
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}

	@Override
	public RoleDto getRole(RoleDto roleDto) {
		Optional<RoleEntity> storedRole = rolesDao.findById(roleDto.getId());
		if(storedRole.isPresent()) {
			RoleDto storedRoleDto = new RoleDto();
			RoleEntity roleEntity = storedRole.get();
			BeanUtils.copyProperties(roleEntity, storedRoleDto);
			return storedRoleDto;
		}
		return null;
	}

}
