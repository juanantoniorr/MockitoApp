package com.jrosas.test.springboot.app.springboottest;

import com.jrosas.test.springboot.app.springboottest.exceptions.InsuficientMoneyException;
import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.models.Bank;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import com.jrosas.test.springboot.app.springboottest.repos.BankRepository;
import com.jrosas.test.springboot.app.springboottest.services.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
class SpringbootTestApplicationTests {
	@Mock //@MockBean same annotation but with spring boot
	AccountRepository accountRepository;
	@Mock //@MockBean same annotation but with spring boot
	BankRepository bankRepository;
	@InjectMocks //needs implementation, @Autowired with spring boot no need implementation
	AccountServiceImpl accountService;

	@BeforeEach
	void setUp() {
		//Replaced for @Mock annotattions
		//accountRepository = mock(AccountRepository.class);
		//bankRepository = mock(BankRepository.class);
		//accountService = new AccountServiceImpl(accountRepository,bankRepository);
	}

	@Test
	void contextLoads() {
		when(accountRepository.findById(1L)).thenReturn(Optional.of(Data.ACCOUNT_001));
		when(accountRepository.findById(2L)).thenReturn(Optional.of(Data.ACCOUNT_002));
		when(bankRepository.findById(1L)).thenReturn(Optional.of(Data.BANK_001));

		BigDecimal originBalance = accountService.checkBalance(1L);
		BigDecimal targetBalance = accountService.checkBalance(2L);

		assertEquals("1000", originBalance.toString());
		assertEquals("2000", targetBalance.toString());

		accountService.transfer(1L,2L,new BigDecimal(500), 1L);

		originBalance = accountService.checkBalance(1L);
		targetBalance = accountService.checkBalance(2L);

		assertEquals("500", originBalance.toString());
		assertEquals("2500", targetBalance.toString());

		//verify how many times method has been invoked by default 1
		verify(accountRepository, times(3)).findById(1L);
		verify(accountRepository, times(3)).findById(2L);
		verify(accountRepository, times(2)).save(any(Account.class));
		verify(bankRepository).findById(1L);
		verify(bankRepository).save(any(Bank.class));

		int total = accountService.checkTransactionTotal(1L);
		assertEquals(1,total);




	}

	@Test
	void testException() {
		when(accountRepository.findById(1L)).thenReturn(Optional.of(Data.ACCOUNT_001));
		when(accountRepository.findById(2L)).thenReturn(Optional.of(Data.ACCOUNT_002));
		when(bankRepository.findById(1L)).thenReturn(Optional.of(Data.BANK_001));

		BigDecimal originBalance = accountService.checkBalance(1L);
		BigDecimal targetBalance = accountService.checkBalance(2L);

		assertEquals("1000", originBalance.toString());
		assertEquals("2000", targetBalance.toString());

		assertThrows(InsuficientMoneyException.class, () -> {
			accountService.transfer(1L,2L,new BigDecimal(1500), 1L);
		} );



		originBalance = accountService.checkBalance(1L);
		targetBalance = accountService.checkBalance(2L);

		assertEquals("1000", originBalance.toString());
		assertEquals("2000", targetBalance.toString());

		verify(bankRepository, never()).save(any(Bank.class));
	}

	@Test
	public void assertSame(){

		when(accountRepository.findById(1L)).thenReturn(Optional.of(Data.ACCOUNT_001));

		Account account1 = accountService.findById(1L);
		Account account2 = accountService.findById(1L);
		//check if both objects are equal
		Assertions.assertSame(account1, account2);

	}

}
