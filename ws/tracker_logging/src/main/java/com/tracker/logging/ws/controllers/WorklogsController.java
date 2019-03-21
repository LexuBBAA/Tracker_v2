package com.tracker.logging.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorklogsController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
}
