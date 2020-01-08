package com.tracker.users.repository;

import com.tracker.users.model.Contract;
import com.tracker.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAll();

    Contract deleteById(long id);

    <S extends Contract> S save(S s);
}
