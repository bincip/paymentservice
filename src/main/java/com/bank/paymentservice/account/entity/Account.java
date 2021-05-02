package com.bank.paymentservice.account.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNumber;

	private String iban;

	private String swiftcode;

	private String currency;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean jointFlag;

	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active = true;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean transactionBlocked;

	private BigDecimal accountBalance;

	private BigDecimal ammountOnHold;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Customer customer;

}
