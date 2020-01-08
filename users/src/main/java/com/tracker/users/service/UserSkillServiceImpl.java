package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.model.User;
import com.tracker.users.model.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @Override
    public UserSkill save(UserSkill userSkill) {

        User user = userService.getUser(userSkill.getUserId());
        Skill skill = skillService.getSkill(userSkill.getSkillId());
        if (user != null && skill != null) {
            user.getSkills().add(skill);
            userService.save(user);
            return userSkill;
        }
        return null;
    }
}
