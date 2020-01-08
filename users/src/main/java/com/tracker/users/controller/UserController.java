package com.tracker.users.controller;

import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.User;
import com.tracker.users.service.UserService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(headers = "Accept=application/json")
    public RestResponse save(@RequestBody User user) {

        User saved = userService.save(user);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_USER, Constants.UNSUCCESSFULLY_ADDED_USER);
    }

    @DeleteMapping
    public RestResponse delete(@RequestParam long id) {

        User delete = userService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_USER, Constants.UNSUCCESSFULLY_REMOVED_USER);
    }
}
