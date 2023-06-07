package com.jrosas.test.springboot.app.springboottest.services;

import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.models.Bank;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import com.jrosas.test.springboot.app.springboottest.repos.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public int checkTransactionTotal(Long bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        return bank.get().getTotalTransaction();
    }

    @Override
    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return account.getBalance();
    }

    @Override
    public void transfer(Long originAccountId, Long targetAccountId, BigDecimal amount, Long bankId) {
        //We should not get id by parameter just testing
    Account originAccount = accountRepository.findById(originAccountId).orElseThrow();
    originAccount.debit(amount);
    accountRepository.save(originAccount);

    Account targetAccount = accountRepository.findById(targetAccountId).orElseThrow();
    targetAccount.credit(amount);
    accountRepository.save(targetAccount);
        Optional<Bank> bank = bankRepository.findById(bankId);
        int totalTransaction = bank.get().getTotalTransaction();
        bank.get().setTotalTransaction(++totalTransaction);
        bankRepository.save(bank.get());
    }
}
