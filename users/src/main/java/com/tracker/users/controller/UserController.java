package com.tracker.users.controller;

import com.tracker.users.model.User;
import com.tracker.users.service.UserService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Api(description = "The users controller", name = "Users services")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiMethod(description = "Method that allows to add a new user")
    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject ResponseEntity<String> save(@ApiBodyObject @RequestBody User user) {

        User saved = userService.save(user);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_USER, Constants.UNSUCCESSFULLY_ADDED_USER);
    }

    @ApiMethod(description = "Method that returns all users")
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject ResponseEntity<List<User>> getUsers() {

        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that returns user by id")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject ResponseEntity<User> getUserById(@ApiPathParam(name = "id", description = "The id") @PathVariable Long id) {

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that searches users by fields")
    @RequestMapping(value = "/users/search", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject ResponseEntity<List<User>> getUser(@ApiQueryParam(name = "firstName", description = "The first name of the searched users", required = false) String firstName,
                                              @ApiQueryParam(name = "lastName", description = "The last name of the searched users", required = false) String lastName,
                                              @ApiQueryParam(name = "userName", description = "The user name of the searched users", required = false) String userName,
                                              @ApiQueryParam(name = "email", description = "The email of the searched users", required = false) String email,
                                              @ApiQueryParam(name = "phone", description = "The phone of the searched users", required = false) String phone,
                                              @ApiQueryParam(name = "yearBorn", description = "The year of birth of the searched users", required = false) Integer yearBorn,
                                              @ApiQueryParam(name = "experience", description = "The minimum experience of the searched users", required = false) Float experience,
                                              @ApiQueryParam(name = "skillIds", description = "The skills of the searched users", required = false) Long[] skillIds
    ) {
        return new ResponseEntity<>(userService.searchUser(firstName, lastName, userName, email, phone, yearBorn, experience, new HashSet<>(Arrays.asList(skillIds))), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that deletes a user by his id")
    @RequestMapping(value = "/users", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject ResponseEntity<String> delete(@RequestParam long id) {

        User delete = userService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_USER, Constants.UNSUCCESSFULLY_REMOVED_USER);
    }
}
