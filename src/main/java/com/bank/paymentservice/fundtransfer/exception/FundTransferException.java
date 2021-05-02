package com.bank.paymentservice.fundtransfer.exception;

public class FundTransferException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FundTransferException(String code, String message) {
		super(code + ":" +message);
	}
	
	public FundTransferException(String message) {
		super(message);
	}
}
