package com.tracker.users.controller;

import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.User;
import com.tracker.users.service.UserService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "The users controller", name = "Users services")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse save(@ApiBodyObject @RequestBody User user) {

        User saved = userService.save(user);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_USER, Constants.UNSUCCESSFULLY_ADDED_USER);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.DELETE)
    public RestResponse delete(@RequestParam long id) {

        User delete = userService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_USER, Constants.UNSUCCESSFULLY_REMOVED_USER);
    }
}
