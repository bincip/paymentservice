package com.bank.paymentservice.common.constants;

import lombok.Getter;

public enum AccountType {
	SA("Savings Account"),
	CA("Current Account"),
	IA("Investment Account");
	
	@Getter
	private String value;
	
	private AccountType(String value) {
		this.value = value;
	}
}
