package com.bank.paymentservice.account.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String fieldName, String fieldValue) {
		super("Unable to find Customer with " + fieldName + ": " + fieldValue);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
