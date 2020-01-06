package com.tracker.users.controller;

import com.tracker.users.model.Skill;
import com.tracker.users.model.SuccessfulResponse;
import com.tracker.users.service.SkillsService;
import com.tracker.users.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @GetMapping
    public List<Skill> getSkills() {
        return skillsService.getSkills();
    }

    @PostMapping(headers = "Accept=application/json")
    public SuccessfulResponse saveSkill(@RequestBody Skill skill) {
        System.out.println("adding skill: " + skill);

        Skill savedSkill = skillsService.save(skill);

        return SuccessfulResponse.builder()
                .message(Constants.ADD_USER_SUCCESS)
                .successfullySavedItems(Arrays.asList(savedSkill))
                .build();
    }
}
