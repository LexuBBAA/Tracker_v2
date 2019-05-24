package com.tracker.firebase.ws.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.firebase.ws.datasource.requests.SendNotificationPayload;
import com.tracker.firebase.ws.datasource.responses.MulticastResponse;
import com.tracker.firebase.ws.datasource.responses.SendToDeviceResponse;
import com.tracker.firebase.ws.services.FcmService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class FirebaseController {

	@Autowired
	private FcmService fcmService;

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}

	@PostMapping("/send")
	public ResponseEntity pushMessageToDevice(
			@RequestParam(value = "targetDeviceId") String deviceId,
			@RequestBody SendNotificationPayload payload
	) {
		try {
			String result = fcmService.sendNotification(deviceId, payload.title, payload.message, payload.data);
			if(!result.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				MulticastResponse response = mapper.readValue(result, MulticastResponse.class);
				if(response.getResults().size() > 1) return new ResponseEntity<>(response, HttpStatus.OK);
				return new ResponseEntity<>(response.getResults().get(0), HttpStatus.OK);
			}

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
