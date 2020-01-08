package com.tracker.users.controller;

import com.tracker.users.model.Skill;
import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.UserSkill;
import com.tracker.users.service.UserSkillService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
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
    public RestResponse addSkillToUser(@RequestBody UserSkill userSkill) {

        UserSkill saved = userSkillService.save(userSkill);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_SKILL_TO_USER, Constants.UNSUCCESSFULLY_ADDED_SKILL_TO_USER);
    }
}
