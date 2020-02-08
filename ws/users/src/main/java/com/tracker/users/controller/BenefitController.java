package com.tracker.users.controller;

import com.tracker.users.model.Benefit;
import com.tracker.users.service.BenefitService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benefits")
public class BenefitController {


    @Autowired
    private BenefitService benefitService;

    @GetMapping
    public List<Benefit> getBenefits() {
        return benefitService.getBenefits();
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<String> save(@RequestBody Benefit benefit) {

        Benefit saved = benefitService.save(benefit);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_BENEFIT, Constants.UNSUCCESSFULLY_ADDED_BENEFIT);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam long id) {

        Benefit delete = benefitService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_BENEFIT, Constants.UNSUCCESSFULLY_REMOVED_BENEFIT);
    }
}
