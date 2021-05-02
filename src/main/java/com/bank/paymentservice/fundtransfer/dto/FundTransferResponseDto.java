package com.bank.paymentservice.fundtransfer.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FundTransferResponseDto {

	private String status;
	private BigDecimal paidAmount;
	private BigDecimal accountBalance;
	private String accountTo;
	private String transactionRefNo;

}
