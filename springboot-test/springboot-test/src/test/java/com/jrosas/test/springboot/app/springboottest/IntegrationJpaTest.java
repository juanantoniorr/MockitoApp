package com.jrosas.test.springboot.app.springboottest;

import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@DataJpaTest
public class IntegrationJpaTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    void testFindById(){
       Optional<Account> account =  accountRepository.findById(1L);
       assertTrue(account.isPresent());
       //From import.sql
       assertEquals("Juan", account.get().getName());
    }

    @Test
    void testFindByName(){
        Optional<Account> account =  accountRepository.findByName("Juan");
        assertTrue(account.isPresent());
        //From import.sql
        assertEquals("Juan", account.get().getName());
    }

}
