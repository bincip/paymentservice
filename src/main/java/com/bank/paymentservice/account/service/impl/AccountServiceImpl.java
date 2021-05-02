package com.bank.paymentservice.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.exception.AccountNotFoundException;
import com.bank.paymentservice.account.mapper.AccountMapper;
import com.bank.paymentservice.account.repository.AccountRepository;
import com.bank.paymentservice.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountMapper accountMapper;

	@Override
	public Account getAccountByAccNumber(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account Number", String.valueOf(accountNumber)));
	}

	@Override
	public AccountDto getAccountDetailsByAccNumber(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account Number", String.valueOf(accountNumber)));
		return accountMapper.accountToAccountDto(account);
	}

	@Override
	public AccountDto getAccountDetailsByIban(String iban) {
		Account account = accountRepository.findByIban(iban).orElseThrow(() -> new AccountNotFoundException("IBAN ", String.valueOf(iban)));
		return accountMapper.accountToAccountDto(account);
	}

	@Override
	public Account getAccountByIban(String iban) {
		return accountRepository.findByIban(iban).orElseThrow(() -> new AccountNotFoundException("IBAN ", String.valueOf(iban)));
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}

}
