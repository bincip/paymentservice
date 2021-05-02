package com.bank.paymentservice.account.service;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.entity.Account;

public interface AccountService {

	AccountDto getAccountDetailsByAccNumber(String accountNumber);
	
	AccountDto getAccountDetailsByIban(String iban);

	Account getAccountByAccNumber(String accountNumber);
	
	Account getAccountByIban(String iban);

	void save(Account sourceAccount);

}
