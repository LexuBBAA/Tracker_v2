package com.tracker.auth.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrackerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerAuthApplication.class, args);
	}

}
