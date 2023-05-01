package com.jrosas.test.springboot.app.springboottest.services;

import com.jrosas.test.springboot.app.springboottest.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account findById(Long id);
    int checkTransactionTotal(Long bankId);
    BigDecimal checkBalance(Long accountId);
    void transfer(Long originAccountId, Long targetAccountId, BigDecimal amount, Long bankId );
}
