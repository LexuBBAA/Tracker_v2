package com.tracker.users.controller;

import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.model.User;
import com.tracker.users.service.UsersService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<User> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping(headers="Accept=application/json")
    public SuccessfulResponse saveUser(@RequestBody User user) {
        User godza = Utils.getGodza();
        User lexu = Utils.getLexu();

        System.out.println(usersService.getUsers().contains(lexu));

        User savedGodza = usersService.saveAndFlush(godza);
        User savedLexu = usersService.save(lexu);
        User savedUser = usersService.save(user);

        return SuccessfulResponse.builder()
                .message(Constants.ADD_USER_SUCCESS)
                .successfullySavedItems(Arrays.asList(savedGodza, savedLexu, savedUser))
                .build();
    }
}
