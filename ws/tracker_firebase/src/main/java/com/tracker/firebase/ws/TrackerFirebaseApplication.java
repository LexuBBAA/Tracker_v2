package com.tracker.firebase.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrackerFirebaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerFirebaseApplication.class, args);
	}

}
