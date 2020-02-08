package com.tracker.users.controller;

import com.tracker.users.model.Skill;
import com.tracker.users.model.User;
import com.tracker.users.service.SkillService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@Api(description = "The skills controller", name = "Skills services")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @ApiMethod(description = "Method that allows to add a new skill")
    @RequestMapping(value = "/skills", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<String> save(@ApiBodyObject @RequestBody Skill skill) {

        Skill saved = skillService.save(skill);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_USER, Constants.UNSUCCESSFULLY_ADDED_USER);
    }

    @ApiMethod(description = "Method that returns all skills")
    @RequestMapping(value = "/skills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<List<Skill>> getSkills() {

        return new ResponseEntity<>(skillService.getSkills(), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that returns skill by id")
    @RequestMapping(value = "/skills/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<Skill> getUserById(@ApiPathParam(name = "id", description = "The id") @PathVariable Long id) {

        return new ResponseEntity<>(skillService.getSkill(id), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that searches skills by fields")
    @RequestMapping(value = "/skills/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<List<Skill>> getUser(@ApiQueryParam(name = "name", description = "The name of the searched skills", required = false) String name,
                                        @ApiQueryParam(name = "description", description = "The description of the searched skills", required = false) String description,
                                        @ApiQueryParam(name = "type", description = "The type of the searched skills", required = false) String type
    ) {
        return new ResponseEntity<>(skillService.searchSkill(name, description, type), HttpStatus.OK);
    }

    @ApiMethod(description = "Method that deletes a skill by his id")
    @RequestMapping(value = "/skills", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject
    ResponseEntity<String> delete(@RequestParam long id) {

        Skill delete = skillService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_USER, Constants.UNSUCCESSFULLY_REMOVED_USER);
    }
}
