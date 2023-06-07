package com.jrosas.test.springboot.app.springboottest.repos;

import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
Account findByName(String name);
}
