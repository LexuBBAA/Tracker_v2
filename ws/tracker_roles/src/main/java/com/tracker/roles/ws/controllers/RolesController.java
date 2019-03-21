package com.tracker.roles.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.roles.ws.dtos.RoleDto;
import com.tracker.roles.ws.entities.responses.RoleResponse;
import com.tracker.roles.ws.entities.responses.RolesResponse;
import com.tracker.roles.ws.services.RolesService;

@RestController
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	
	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
	@GetMapping("/roles")
	public RolesResponse getRoles() {
		RolesResponse response = new RolesResponse();
		
		List<RoleDto> storedRoles = rolesService.getRoles();
		ArrayList<RoleResponse> roles = new ArrayList<RoleResponse>();
		for(RoleDto roleDto: storedRoles) {
			RoleResponse roleResponse = new RoleResponse();
			BeanUtils.copyProperties(roleDto, roleResponse);
			roles.add(roleResponse);
		}
		
		response.setRoles(roles);
		return response;
	}
	
	@GetMapping("/{id}")
	public RoleResponse getRole(@PathVariable int id) {
		RoleResponse response = new RoleResponse();
		
		RoleDto roleDto = new RoleDto();
		roleDto.setId(id);
		
		RoleDto storedRole = rolesService.getRole(roleDto);
		BeanUtils.copyProperties(storedRole, response);
		
		return response;
	}
	
}
