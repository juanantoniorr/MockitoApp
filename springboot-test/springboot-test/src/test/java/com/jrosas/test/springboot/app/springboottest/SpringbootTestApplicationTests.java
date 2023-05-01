package com.jrosas.test.springboot.app.springboottest;

import static org.mockito.Mockito.*;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import com.jrosas.test.springboot.app.springboottest.repos.BankRepository;
import com.jrosas.test.springboot.app.springboottest.services.AccountService;
import com.jrosas.test.springboot.app.springboottest.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTestApplicationTests {
	AccountRepository accountRepository;
	BankRepository bankRepository;
	AccountService accountService;

	@BeforeEach
	void setUp() {
		accountRepository = mock(AccountRepository.class);
		bankRepository = mock(BankRepository.class);
		accountService = new AccountServiceImpl(accountRepository,bankRepository);
	}

	@Test
	void contextLoads() {
	}

}
