package com.tracker.users.utils;

import com.tracker.users.model.Skill;
import com.tracker.users.model.UserSkill;
import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.User;
import com.tracker.users.model.rest.Type;

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

    private static RestResponse buildUnsuccessfulResponse(String message) {
        return RestResponse.builder().type(Type.NOT_OK).message(message).build();
    }

    private static RestResponse buildSuccessfulResponse(String message, List<Object> entities) {
        return RestResponse.builder().type(Type.OK).message(message).successfullySavedItems(entities).build();
    }

    public static RestResponse getRestResponse(Object entity, String successfulMessage, String unsuccessfulMessage) {

        if (entity != null) {
            return Utils.buildSuccessfulResponse(successfulMessage, Collections.singletonList(entity));
        }

        return Utils.buildUnsuccessfulResponse(unsuccessfulMessage);
    }
}
