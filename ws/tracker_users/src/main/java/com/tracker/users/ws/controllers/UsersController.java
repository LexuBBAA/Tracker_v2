package com.tracker.users.ws.controllers;

import com.tracker.users.ws.datasource.dto.UserDto;
import com.tracker.users.ws.datasource.dto.UserPreviewDto;
import com.tracker.users.ws.datasource.services.impl.UsersPreviewServiceImpl;
import com.tracker.users.ws.datasource.services.impl.UsersServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UsersController {

	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private UsersPreviewServiceImpl usersPreviewService;

    @GetMapping("/")
    public String getStatus() {
        return "Available";
    }

    @SuppressWarnings(value = "rawtypes")
    @GetMapping("/users")
    public ResponseEntity getUserPreviews() {
        List<UserPreviewDto> dtos = usersPreviewService.findAll();
        if(dtos.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<UserPreviewDto>>(dtos, HttpStatus.OK);
    }

    @SuppressWarnings(value = "rawtypes")
    @GetMapping("/{userId}")
    public ResponseEntity getUserDetails(
            @PathVariable(name = "userId") String userId
    ) {
        UserDto dto = usersService.getUserById(userId);
        if(dto == null) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @SuppressWarnings(value = "rawtypes")
    @GetMapping("/{userId}/preview")
    public ResponseEntity getUserPreview(
            @PathVariable(value = "userId") String userId
    ) {
        UserPreviewDto dto = usersPreviewService.findById(userId);
        if(dto == null) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<UserPreviewDto>(dto, HttpStatus.OK);
    }

    @SuppressWarnings(value = "rawtypes")
    @PostMapping("/")
    public ResponseEntity createNewUser(@RequestBody UserDto newUser) {
        if(newUser.userId != null) {
        	newUser.userId = null;
        }
        
        UserDto dto = usersService.createUser(newUser);
        if(dto == null || dto.id == null) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @SuppressWarnings(value = "rawtypes")
    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody UserDto user) {
        if(user.userId == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        UserDto dto = usersService.updateUser(user);
        if(dto == null) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @SuppressWarnings(value = "rawtypes")
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable(value = "userId") String userId) {
        if(usersService.deleteUserById(userId)) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @SuppressWarnings(value = "rawtypes")
    @GetMapping("/createdby/{userId}")
    public ResponseEntity getUsersCreatedBy(
            @PathVariable(value = "userId") String userId
    ) {
        List<UserPreviewDto> dtos = usersPreviewService.findAllByCreatedById(userId);
        if(dtos.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<UserPreviewDto>>(dtos, HttpStatus.OK);
    }

}