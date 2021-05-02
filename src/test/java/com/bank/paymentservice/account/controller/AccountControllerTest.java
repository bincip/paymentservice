package com.bank.paymentservice.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.service.AccountService;
import com.bank.paymentservice.account.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	
	@InjectMocks
	AccountController accountController;

	@Mock
	AccountService accountService;
	
	private final String ACC_NUMBER = "1234";
	private final String IBAN = "0001234";

	@Test
	public void getAccountsByAccNumberTest() {
		when(accountService.getAccountDetailsByAccNumber(ACC_NUMBER)).thenReturn(getAccount());
		ResponseEntity<AccountDto> response = accountController.getAccountsByAccNumber(ACC_NUMBER);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getAccountsByIbanTest() {
		when(accountService.getAccountDetailsByIban(IBAN)).thenReturn(getAccount());
		ResponseEntity<AccountDto> response = accountController.getAccountsByIban(IBAN);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private AccountDto getAccount() {
		return TestUtils.getAccountDto(ACC_NUMBER, IBAN, new BigDecimal(20000), "000");
	}

}
