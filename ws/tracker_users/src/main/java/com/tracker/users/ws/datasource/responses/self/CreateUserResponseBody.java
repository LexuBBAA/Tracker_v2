package com.tracker.users.ws.datasource.responses.self;

import com.tracker.users.ws.datasource.dto.UserDto;

public class CreateUserResponseBody {

    public Integer id;

    public CreateUserResponseBody(UserDto userDto) {
        this.id = userDto.userId;
    }

}
