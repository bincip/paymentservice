package com.bank.paymentservice.account.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {
	
	@InjectMocks
	AccountMapper accountMapper;
	
	private final String ACC_NUMBER = "1234";

	@Test
	public void accountToAccountDtoTest() {
		AccountDto account = accountMapper.accountToAccountDto(getAccount());
		assertNotNull(account);
		assertEquals(ACC_NUMBER, account.getAccountNumber());
		assertEquals(new BigDecimal(20000), account.getAccountBalance());
	}
	
	@Test
	public void accountDtoToAccountTest() {
		Account account = accountMapper.accountDtoToAccount(getAccountDto());
		assertNotNull(account);
		assertEquals(ACC_NUMBER, account.getAccountNumber());
		assertEquals(new BigDecimal(20000), account.getAccountBalance());
	}
	
	private AccountDto getAccountDto() {
		return TestUtils.getAccountDto(ACC_NUMBER, "0001234", new BigDecimal(20000), "000");
	}

	private Account getAccount() {
		return TestUtils.getAccount(ACC_NUMBER, "0001234", new BigDecimal(20000), "000");
	}
}
