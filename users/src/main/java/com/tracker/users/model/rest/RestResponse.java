package com.tracker.users.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponse {

    private Type type;
    private String message;
    private List<Object> successfullySavedItems;
}
