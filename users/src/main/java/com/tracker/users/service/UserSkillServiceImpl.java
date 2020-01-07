package com.tracker.users.service;

import com.tracker.users.model.Skill;
import com.tracker.users.model.User;
import com.tracker.users.model.UserSkill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private SkillsService skillsService;

    @Autowired
    private UsersService usersService;

    @Override
    public UserSkill save(UserSkill userSkill) {

        User user = usersService.getUser(userSkill.getUserId());
        Skill skill = skillsService.getSkill(userSkill.getSkillId());
        if (user != null && skill != null) {
            user.getSkills().add(skill);
            usersService.save(user);
            return userSkill;
        }
        return null;
    }
}
