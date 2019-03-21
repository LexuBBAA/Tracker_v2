package com.tracker.auth.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class TrackerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerAuthApplication.class, args);
	}

}
