package com.tracker.users.utils;

import com.tracker.users.model.Skill;
import com.tracker.users.model.UserSkill;
import com.tracker.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Utils {


    public static User getGodza() {
        return User.builder()
                .firstName("Mihai")
                .lastName("Godza")
                .userName("mgodza93")
                .birthday(new Date(0))
                .email("mgodza93@gmail.com")
                .phone("+40123123123")
                .experience(0f)
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
                .experience(0f)
                .build();
    }

    private static ResponseEntity<String> buildUnsuccessfulResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    private static ResponseEntity<String> buildSuccessfulResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public static ResponseEntity<String> getRestResponse(Object entity, String successfulMessage, String unsuccessfulMessage) {

        if (entity != null) {
            return Utils.buildSuccessfulResponse(successfulMessage);
        }

        return Utils.buildUnsuccessfulResponse(unsuccessfulMessage);
    }
}
