package com.tracker.logging.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class TrackerLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerLoggingApplication.class, args);
	}

}
