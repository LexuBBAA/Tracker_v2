package com.tracker.users.service;

import com.tracker.users.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractBenefitServiceImpl implements ContractBenefitService {

    @Autowired
    private ContractService contractService;

    @Autowired
    private BenefitService benefitService;

    @Override
    public ContractBenefit save(ContractBenefit contractBenefit) {

        Contract contract = contractService.getContract(contractBenefit.getContractId());
        Benefit benefit = benefitService.getBenefit(contractBenefit.getBenefitId());
        if (contract != null && benefit != null) {
            contract.getBenefits().add(benefit);
            contractService.save(contract);
            return contractBenefit;
        }
        return null;
    }
}
