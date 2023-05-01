package com.jrosas.test.springboot.app.springboottest.repos;

import com.jrosas.test.springboot.app.springboottest.models.Bank;

import java.util.List;

public interface BankRepository {
    List<Bank> findAll();
    Bank findById(Long id);
    void update (Bank bank);

}
