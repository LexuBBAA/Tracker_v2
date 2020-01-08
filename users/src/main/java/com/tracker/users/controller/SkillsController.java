package com.tracker.users.controller;

import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.Skill;
import com.tracker.users.service.SkillsService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public RestResponse save(@RequestBody Skill skill) {

        Skill saved = skillsService.save(skill);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_SKILL, Constants.UNSUCCESSFULLY_ADDED_SKILL);
    }

    @DeleteMapping
    public RestResponse deleteSkill(@RequestParam long id) {

        Skill delete = skillsService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_SKILL, Constants.UNSUCCESSFULLY_REMOVED_SKILL);
    }


}
