package com.tracker.projects.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectsController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
}
