package com.tracker.users.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
}