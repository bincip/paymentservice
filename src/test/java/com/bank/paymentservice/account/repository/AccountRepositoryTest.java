package com.bank.paymentservice.account.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.entity.Customer;
import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest  {
	
	@Autowired
	AccountRepository accountRepository;

	@Test
	public void saveAccount() {
		Account account = accountRepository.save(getAccount());
		assertNotNull(account.getId());
		assertEquals(true, account.isActive());
		assertEquals(false, account.isTransactionBlocked());
		assertEquals(false, account.isJointFlag());
	}
	
	@Test
	public void saveAccount_blockedTransaction() {
		Account account = accountRepository.save(getTransactionBlockedAccount());
		assertNotNull(account.getId());
		assertEquals(true, account.isActive());
		assertEquals(true, account.isTransactionBlocked());
		assertEquals(false, account.isJointFlag());
	}

	private Account getTransactionBlockedAccount() {
		Account account = getAccount() ;
		account.setTransactionBlocked(true);
		return account;
	}

	private Account getAccount() {
		Account account = TestUtils.getAccount("1234", "0001234", new BigDecimal(20000), "000");
		Customer customer = account.getCustomer();
		customer.setId((long) 1);
		account.setCustomer(customer);
		account.setCreatedOn(LocalDateTime.now());
		return account;
	}

	
}
