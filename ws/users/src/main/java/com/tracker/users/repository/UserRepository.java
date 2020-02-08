package com.tracker.users.repository;

import com.tracker.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User deleteById(long id);

    <S extends User> S save(S s);
}
