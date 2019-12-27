package com.tracker.users.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SuccessfulResponse {

    private String message;
    private List<Object> successfullySavedItems = new ArrayList<>();
}
