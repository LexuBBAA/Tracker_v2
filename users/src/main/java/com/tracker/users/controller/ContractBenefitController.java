package com.tracker.users.controller;

import com.tracker.users.model.ContractBenefit;
import com.tracker.users.model.UserSkill;
import com.tracker.users.model.rest.RestResponse;
import com.tracker.users.service.ContractBenefitService;
import com.tracker.users.service.UserSkillService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addBenefitToContract")
public class ContractBenefitController {

    @Autowired
    private ContractBenefitService contractBenefitService;

    @PostMapping
    public RestResponse add(@RequestBody ContractBenefit contractBenefit) {

        ContractBenefit saved = contractBenefitService.save(contractBenefit);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_SKILL_TO_USER, Constants.UNSUCCESSFULLY_ADDED_SKILL_TO_USER);
    }
}
