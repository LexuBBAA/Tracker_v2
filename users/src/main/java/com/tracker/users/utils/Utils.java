package com.tracker.users.utils;

import com.tracker.users.model.User;

import java.util.Date;

public class Utils {


    public static User getGodza() {
        return User.builder()
                .firstName("Mihai")
                .lastName("Godza")
                .userName("mgodza93")
                .birthday(new Date(0))
                .email("mgodza93@gmail.com")
                .phone("+40123123123")
                .experience(0)
                .build();
    }

    public static User getLexu() {
        return User.builder()
                .firstName("Bogdan")
                .lastName("Andrei Alexandru")
                .userName("Lexu")
                .birthday(new Date(1))
                .email(".birsasteanu@gmail.com")
                .phone("+40456456456")
                .experience(0)
                .build();
    }
}
