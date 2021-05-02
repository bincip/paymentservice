package com.bank.paymentservice.account.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bank.paymentservice.common.constants.AccountType;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;

	private String accountNumber;

	private String iban;

	private String swiftcode;

	private String currency;

	private boolean jointFlag;

	private boolean active;

	private BigDecimal accountBalance;

	private BigDecimal ammountOnHold;

	private AccountType accountType;

	private LocalDateTime createdOn;

	private CustomerDto customer;

}
