package com.tracker.users.controller;

import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.model.User;
import com.tracker.users.repository.UsersRepository;
import com.tracker.users.service.UserService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(headers="Accept=application/json")
    public SuccessfulResponse saveUser(@RequestBody User user) {
        System.out.println("user=" + user);

        User godza = Utils.getGodza();
        User lexu = Utils.getLexu();

        User savedGodza = userService.saveAndFlush(godza);
        User savedLexu = userService.save(lexu);
        User savedUser = userService.save(user);

        return SuccessfulResponse.builder()
                .message(Constants.ADD_USER_SUCCESS)
                .successfullySavedItems(Arrays.asList(savedGodza, savedLexu, savedUser))
                .build();
    }
}
