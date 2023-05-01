package com.jrosas.test.springboot.app.springboottest.services;

import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.models.Bank;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import com.jrosas.test.springboot.app.springboottest.repos.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public int checkTransactionTotal(Long bankId) {
        Bank bank = bankRepository.findById(bankId);
        return bank.getTotalTransaction();
    }

    @Override
    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId);
        return account.getBalance();
    }

    @Override
    public void transfer(Long originAccountId, Long targetAccountId, BigDecimal amount, Long bankId) {
        //We should not get id by parameter just testing
    Bank bank = bankRepository.findById(bankId);
    int totalTransaction = bank.getTotalTransaction();
    bank.setTotalTransaction(++totalTransaction);
    bankRepository.update(bank);
    Account originAccount = accountRepository.findById(originAccountId);
    originAccount.debit(amount);
    accountRepository.update(originAccount);

    Account targetAccount = accountRepository.findById(targetAccountId);
    targetAccount.credit(amount);
    accountRepository.update(targetAccount);
    }
}
