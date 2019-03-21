package com.tracker.firebase.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseController {

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}
	
}
