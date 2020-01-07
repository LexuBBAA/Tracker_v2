package com.tracker.users.controller;

import com.tracker.users.model.Skill;
import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.model.User;
import com.tracker.users.model.UserSkill;
import com.tracker.users.repository.UsersSkillRepository;
import com.tracker.users.service.SkillsService;
import com.tracker.users.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
public class UserSkillController {

    @Autowired
    private UsersSkillRepository usersSkillRepository;

    @Autowired
    private SkillsService skillsService;

    @Autowired
    private UsersService usersService;


    @PostMapping("/addSkillToUser")
    public SuccessfulResponse addSkillToUser(UserSkill userSkill) {

        User user = usersService.getUser(userSkill.getUserId());
        Skill skill = skillsService.getSkill(userSkill.getUserId());

        if (user != null && skill != null) {
            usersSkillRepository.save(userSkill);
            return SuccessfulResponse.builder().message("Successfully added skill to user").successfullySavedItems(Collections.singletonList(userSkill)).build();
        }
        return SuccessfulResponse.builder().message("Cannot add skill to user").build();
    }
}
