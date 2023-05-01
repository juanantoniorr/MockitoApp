package com.jrosas.test.springboot.app.springboottest.repos;

import com.jrosas.test.springboot.app.springboottest.models.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();
    Account findById(Long id);
    void update(Account account);
}
