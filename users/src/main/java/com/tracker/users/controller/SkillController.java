package com.tracker.users.controller;

import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.model.Skill;
import com.tracker.users.service.SkillService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getSkills() {
        return skillService.getSkills();
    }

    @PostMapping(headers = "Accept=application/json")
    public RestResponse save(@RequestBody Skill skill) {

        Skill saved = skillService.save(skill);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_SKILL, Constants.UNSUCCESSFULLY_ADDED_SKILL);
    }

    @DeleteMapping
    public RestResponse delete(@RequestParam long id) {

        Skill delete = skillService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_SKILL, Constants.UNSUCCESSFULLY_REMOVED_SKILL);
    }
}
