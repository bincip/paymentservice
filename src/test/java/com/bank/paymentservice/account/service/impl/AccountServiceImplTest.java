package com.bank.paymentservice.account.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.dto.CustomerDto;
import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.entity.Customer;
import com.bank.paymentservice.account.exception.AccountNotFoundException;
import com.bank.paymentservice.account.mapper.AccountMapper;
import com.bank.paymentservice.account.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
	
	@InjectMocks
	AccountServiceImpl accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	AccountMapper accountMapper;
	
	private final String ACC_NUMBER = "1234";
	
	private final String IBAN = "0001234";

	@Test
	public void getAccountByAccNumberTest_when_Account_Found() {
		when(accountRepository.findByAccountNumber(ACC_NUMBER)).thenReturn(Optional.of(getAccount()));
		Account account = accountService.getAccountByAccNumber(ACC_NUMBER);
		assertNotNull(account);
		assertEquals(ACC_NUMBER, account.getAccountNumber());
	}
	
	@Test
	public void getAccountByAccNumberTest_when_Account_Not_Found() {
		assertThrows(AccountNotFoundException.class, () -> {
			accountService.getAccountByAccNumber(ACC_NUMBER);
		});
	}

	@Test
	public void getAccountDetailsByAccNumberTest_when_Account_Found() {
		
		Account account = getAccount();
		
		when(accountRepository.findByAccountNumber(ACC_NUMBER)).thenReturn(Optional.of(account));
		when(accountMapper.accountToAccountDto(account)).thenReturn(getAccountDto());
		AccountDto accountDto = accountService.getAccountDetailsByAccNumber(ACC_NUMBER);
		assertNotNull(accountDto);
		assertEquals(ACC_NUMBER, accountDto.getAccountNumber());
	}
	
	@Test
	public void getAccountDetailsByAccNumberTest_when_Account_Not_Found() {
		assertThrows(AccountNotFoundException.class, () -> {
			accountService.getAccountDetailsByAccNumber(ACC_NUMBER);
		});
	}
	
	
	@Test
	public void getAccountByIbanTest_when_Account_Found() {
		when(accountRepository.findByIban(IBAN)).thenReturn(Optional.of(getAccount()));
		Account account = accountService.getAccountByIban(IBAN);
		assertNotNull(account);
		assertEquals(IBAN, account.getIban());
	}
	
	@Test
	public void getAccountByIbanTest_when_Account_Not_Found() {
		assertThrows(AccountNotFoundException.class, () -> {
			accountService.getAccountByIban(IBAN);
		});
	}

	@Test
	public void getAccountDetailsByIbanTest_when_Account_Found() {
		
		Account account = getAccount();
		
		when(accountRepository.findByIban(IBAN)).thenReturn(Optional.of(account));
		when(accountMapper.accountToAccountDto(account)).thenReturn(getAccountDto());
		AccountDto accountDto = accountService.getAccountDetailsByIban(IBAN);
		assertNotNull(accountDto);
		assertEquals(IBAN, accountDto.getIban());
	}
	
	@Test
	public void getAccountDetailsByIbanTest_when_Account_Not_Found() {
		assertThrows(AccountNotFoundException.class, () -> {
			accountService.getAccountDetailsByIban(IBAN);
		});
	}
	
	@Test
	public void save() {
		accountService.save(getAccount());
		Mockito.verify(accountRepository, atMostOnce()).save(getAccount());
	}

	private AccountDto getAccountDto() {
		
		CustomerDto customer = new CustomerDto();
		customer.setId((long) 1);
		customer.setCif("00001");
		
		AccountDto account = new AccountDto();
		account.setId((long) 1);
		account.setAccountNumber(ACC_NUMBER);
		account.setIban(IBAN);
		account.setAccountBalance(new BigDecimal(20000));
		account.setCustomer(customer);
		return account;
	}

	private Account getAccount() {
		
		Customer customer = new Customer();
		customer.setId((long) 1);
		customer.setCif("00001");
		
		Account account = new Account();
		account.setId((long) 1);
		account.setAccountNumber(ACC_NUMBER);
		account.setIban(IBAN);
		account.setAccountBalance(new BigDecimal(20000));
		account.setCustomer(customer);
		return account;
	}
}
