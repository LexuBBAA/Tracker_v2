package com.tracker.users.controller;

import com.tracker.users.model.UserSkill;
import com.tracker.users.service.UserSkillService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "The user skills controller", name = "User skills services")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @ApiMethod(description = "Method that allows to add an existing skill to an existing user")
    @RequestMapping(value = "/userSkills", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<String> add(@ApiBodyObject
                               @RequestBody UserSkill userSkill) {

        UserSkill saved = userSkillService.save(userSkill);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_SKILL_TO_USER, Constants.UNSUCCESSFULLY_ADDED_SKILL_TO_USER);
    }
}
