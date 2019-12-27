package com.tracker.users.controller;

import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.model.User;
import com.tracker.users.repository.UsersRepository;
import com.tracker.users.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    @PostMapping
    public SuccessfulResponse saveUser(@RequestBody String myString) {
        System.out.println("message=" + myString);

        User godza = getGodza();
        User lexu = getLexu();
        User savedGodza = usersRepository.saveAndFlush(godza);
        User savedLexu = usersRepository.save(lexu);
        return SuccessfulResponse.builder()
                .message(Constants.ADD_USER_SUCCESS)
                .successfullySavedItems(Arrays.asList(savedGodza, savedLexu))
                .build();
    }

    private User getGodza() {
        return User.builder()
                .firstName("Mihai")
                .lastName("Godza")
                .userName("mgodza93")
                .birthday(new Date())
                .email("mgodza93@gmail.com")
                .phone("+40123123123")
                .experience(0)
                .build();
    }

    private User getLexu() {
        return User.builder()
                .firstName("Bogdan")
                .lastName("Andrei Alexandru")
                .userName("Lexu")
                .birthday(new Date())
                .email(".birsasteanu@gmail.com")
                .phone("+40456456456")
                .experience(0)
                .build();
    }
}
