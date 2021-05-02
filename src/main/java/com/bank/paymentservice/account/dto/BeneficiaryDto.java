package com.bank.paymentservice.account.dto;

import java.time.LocalDateTime;

import com.bank.paymentservice.common.constants.AccountType;

import lombok.Data;

@Data
public class BeneficiaryDto {
	private Long id;

	private String fullName;
	private String nickname;
	private String accountNumber;
	private String iban;
	private String swiftCode;
	private String currency;

	private String bankName;
	private String branch;
	private String bankCountry;

	private AccountType accountType;

	private boolean active;

	private LocalDateTime createdDate;

	private CustomerDto customer;
}
