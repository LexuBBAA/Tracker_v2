package com.tracker.users.controller;

import com.tracker.users.model.Skill;
import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.model.User;
import com.tracker.users.model.UserSkill;
import com.tracker.users.service.SkillsService;
import com.tracker.users.service.UserSkillService;
import com.tracker.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/addSkillToUser")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @PostMapping
    public SuccessfulResponse addSkillToUser(@RequestBody UserSkill userSkill) {

        UserSkill saved = userSkillService.save(userSkill);
        if (saved != null) {
            return SuccessfulResponse.builder().message("Successfully added skill to user").successfullySavedItems(Collections.singletonList(userSkill)).build();
        }

        return SuccessfulResponse.builder().message("Cannot add skill to user").build();
    }
}
