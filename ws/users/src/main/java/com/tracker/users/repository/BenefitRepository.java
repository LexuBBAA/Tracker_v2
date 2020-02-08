package com.tracker.users.repository;

import com.tracker.users.model.Benefit;
import com.tracker.users.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    List<Benefit> findAll();

    Benefit deleteById(long id);

    <S extends Benefit> S save(S s);
}
