package com.tracker.auth.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
}
