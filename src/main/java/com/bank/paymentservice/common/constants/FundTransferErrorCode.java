package com.bank.paymentservice.common.constants;

import lombok.Getter;

public enum FundTransferErrorCode {
	FT_4000("Something went wrong"),
	FT_4001("Insufficient balance in source account"),
	FT_4002("The Accounts does not belongs to the same CIF"),
	FT_4003("Not able to complete the transaction at the moment"),;
	
	@Getter
	private String value;
	
	private FundTransferErrorCode(String value) {
		this.value = value;
	}
}
