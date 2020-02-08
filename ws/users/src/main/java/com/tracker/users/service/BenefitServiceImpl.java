package com.tracker.users.service;

import com.tracker.users.model.Benefit;
import com.tracker.users.model.Contract;
import com.tracker.users.repository.BenefitRepository;
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
public class BenefitServiceImpl implements BenefitService {

    @Autowired
    private BenefitRepository benefitRepository;

    private ConcurrentMap<Long, Benefit> benefitsById = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = Constants.SYNC_WITH_DB_TIMER)
    public void syncWithDb() {

        benefitsById = benefitRepository.findAll()
                .stream()
                .collect(Collectors.toConcurrentMap(Benefit::getId, Function.identity()));
    }

    @Override
    public List<Benefit> getBenefits() {

        return benefitsById.values()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Benefit getBenefit(long id) {
        return benefitsById.get(id);
    }


    @Override
    public Benefit save(Benefit benefit) {
        return benefitRepository.save(benefit);
    }

    @Override
    public Benefit delete(long id) {
        return benefitRepository.deleteById(id);
    }
}
