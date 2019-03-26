package com.tracker.auth.ws.controllers;

import com.tracker.auth.ws.datasources.models.enums.HttpResponseCode;
import com.tracker.auth.ws.datasources.models.response.ApiResponse;
import com.tracker.auth.ws.datasources.models.response.ErrorResponse;
import com.tracker.auth.ws.datasources.models.response.TokenResponse;
import com.tracker.auth.ws.datasources.tokens.dto.TokenDto;
import com.tracker.auth.ws.datasources.tokens.services.impl.TokensServiceImpl;
import com.tracker.auth.ws.datasources.users.dto.UserDto;
import com.tracker.auth.ws.datasources.users.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class OAuth2Controller {

	@SuppressWarnings("all")
	@Autowired
	private TokensServiceImpl tokensService;

	@SuppressWarnings("all")
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}

	@GetMapping("/token")
	public ApiResponse getToken(
			@RequestHeader(value = "username", required = false) String username,
			@RequestHeader(value = "password"/*, required = false*/) String password,
			@RequestHeader(value = "client-secret", required = false) String clientSecret,
			@RequestHeader(value = "client-id", required = false) String clientId,
			@RequestHeader(value = "deviceId", required = false) String deviceId,
			@RequestHeader(value = "deviceOs", required = false) String deviceOs,
			@RequestHeader(value = "deviceOsVersion", required = false) String deviceOsVersion,
			@RequestHeader(value = "latitude", required = false) Float latitude,
			@RequestHeader(value = "longitude", required = false) Float longitude
	) {
		if(clientId == null || clientSecret == null) {
			return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_FORBIDDEN);
		}

		if(username == null || password == null) {
			return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST);
		}

		if(deviceId == null) {
			return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_BAD_REQUEST);
		}

		UserDto userDto = new UserDto();
		userDto.username = username;
		userDto.password = password;
		userDto.deviceId = deviceId;
		userDto.deviceOS = deviceOs;
		userDto.deviceOsVersion = deviceOsVersion;
		userDto.deviceLastLat = latitude;
		userDto.deviceLastLon = longitude;
		userDto.deviceLastKnownInteraction = new Date(System.currentTimeMillis());

		UserDto authenticatedUser = userService.authenticateUser(userDto);
		TokenDto generatedToken;
		if(authenticatedUser != null) {
			authenticatedUser.deviceId = userDto.deviceId;
			authenticatedUser.deviceOS = userDto.deviceOS;
			authenticatedUser.deviceOsVersion = userDto.deviceOsVersion;
			authenticatedUser.deviceLastLat = userDto.deviceLastLat;
			authenticatedUser.deviceLastLon = userDto.deviceLastLon;
			authenticatedUser.deviceLastKnownInteraction = userDto.deviceLastKnownInteraction;

			generatedToken = tokensService.getToken(authenticatedUser);
		} else {
			return new ErrorResponse(HttpResponseCode.HTTP_RESPONSE_UNAUTHORIZED);
		}

		return new TokenResponse(generatedToken);
	}
}
