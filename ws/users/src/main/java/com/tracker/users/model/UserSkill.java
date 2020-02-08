package com.tracker.users.model;

import lombok.Data;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@ApiObject(name = "UserSkill", description = "Used to add an existing skill to an existing user")
public class UserSkill {

    @ApiObjectField(description = "The id for user")
    private Long userId;

    @ApiObjectField(description = "The id for skill")
    private Long skillId;
}
