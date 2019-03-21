package com.tracker.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tracker.ws.entities.responses.RoleResponse;
import com.tracker.ws.entities.responses.RolesResponse;

@RestController
public class TrackerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
	@GetMapping("/roles")
	public RolesResponse getRoles() {
		RolesResponse rolesResponse = restTemplate.getForObject("http://tracker-roles/roles", RolesResponse.class);
		return rolesResponse;
	}
	
	@GetMapping("/role/{id}")
	public RoleResponse getRole(@PathVariable int id) {
		RoleResponse roleResponse = restTemplate.getForObject("http://localhost:8200/" + id, RoleResponse.class);
		return roleResponse;
	}
	
}