package com.tracker.users.service;

import com.tracker.users.model.Contract;
import com.tracker.users.repository.ContractRepository;
import com.tracker.users.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    private ConcurrentMap<Long, Contract> contractsById = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = Constants.SYNC_WITH_DB_TIMER)
    public void syncWithDb() {

        contractsById = contractRepository.findAll()
                .stream()
                .collect(Collectors.toConcurrentMap(Contract::getId, Function.identity()));
    }

    @Override
    public List<Contract> getContracts() {

        return contractsById.values()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Contract getContract(long id) {
        return contractsById.get(id);
    }


    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract delete(long id) {
        return contractRepository.deleteById(id);
    }
}
