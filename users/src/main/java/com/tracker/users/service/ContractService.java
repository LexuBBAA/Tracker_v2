package com.tracker.users.service;

import com.tracker.users.model.Contract;
import com.tracker.users.model.User;

import java.util.List;

public interface ContractService {

    List<Contract> getContracts();

    Contract getContract(long id);

    Contract save(Contract contract);

    Contract delete(long id);
}
