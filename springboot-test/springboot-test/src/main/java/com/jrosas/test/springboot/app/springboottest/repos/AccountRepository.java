package com.jrosas.test.springboot.app.springboottest.repos;

import com.jrosas.test.springboot.app.springboottest.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.name = ?1")
    Optional<Account> findByName(String name);

}
