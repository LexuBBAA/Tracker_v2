package com.tracker.users.service;

import com.tracker.users.model.Benefit;

import java.util.List;

public interface BenefitService {

    List<Benefit> getBenefits();

    Benefit getBenefit(long id);

    Benefit save(Benefit contract);

    Benefit delete(long id);
}
