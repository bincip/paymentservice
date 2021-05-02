package com.bank.paymentservice.account.util;

import java.math.BigDecimal;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.dto.CustomerDto;
import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.entity.Customer;
import com.bank.paymentservice.common.constants.ServiceType;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;

public class TestUtils {

	public static FundTransferResponseDto getFundTransferResponseDto() {
		FundTransferResponseDto response = new FundTransferResponseDto();
		response.setAccountBalance(new BigDecimal(10500));
		response.setAccountTo("5678");
		response.setPaidAmount(new BigDecimal(1500));
		response.setTransactionRefNo("020520211");
		return response;
	}

	public static FundTransferRequestDto getFundTransferRequestDto() {
		FundTransferRequestDto request = new FundTransferRequestDto();
		request.setFromAccount("1234");
		request.setToAccount("5678");
		request.setServiceType(ServiceType.OAT);
		request.setPurposeCode("OWN_ACC");
		request.setPurposeDesc("Own Acccount Transfer");
		request.setAmount(new BigDecimal(1500));
		return request;
	}
	
	public static Account getAccount(String accNumber, String iban, BigDecimal balance, String cif) {
		
		Customer customer = new Customer();
		customer.setCif(cif);
		
		Account account = new Account();
		account.setAccountNumber(accNumber);
		account.setIban(iban);
		account.setAccountBalance(balance);
		account.setCustomer(customer);
		return account;
	}
	
	public static AccountDto getAccountDto(String accNumber, String iban, BigDecimal balance, String cif) {
		
		CustomerDto customer = new CustomerDto();
		customer.setCif(cif);
		
		AccountDto account = new AccountDto();
		account.setAccountNumber(accNumber);
		account.setIban(iban);
		account.setAccountBalance(balance);
		account.setCustomer(customer);
		return account;
	}
	
}
