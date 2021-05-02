package com.bank.paymentservice.account.exception;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String fieldName, String fieldValue) {
		super("Unable to find Account with " + fieldName + ": " + fieldValue);
	}

	public AccountNotFoundException(String message) {
		super(message);
	}
}
