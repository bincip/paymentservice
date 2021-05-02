package com.bank.paymentservice.account.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.paymentservice.common.constants.AccountType;

import lombok.Data;

@Entity
@Data
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private boolean active;

	private LocalDateTime createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private Customer customer;

}
