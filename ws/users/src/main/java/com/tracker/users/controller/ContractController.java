package com.tracker.users.controller;

import com.tracker.users.model.Contract;
import com.tracker.users.service.ContractService;
import com.tracker.users.utils.Constants;
import com.tracker.users.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<Contract> getContracts() {
        return contractService.getContracts();
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<String> save(@RequestBody Contract contract) {

        Contract saved = contractService.save(contract);
        return Utils.getRestResponse(saved, Constants.SUCCESSFULLY_ADDED_CONTRACT, Constants.UNSUCCESSFULLY_ADDED_CONTRACT);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam long id) {

        Contract delete = contractService.delete(id);
        return Utils.getRestResponse(delete, Constants.SUCCESSFULLY_REMOVED_CONTRACT, Constants.UNSUCCESSFULLY_REMOVED_CONTRACT);
    }
}
