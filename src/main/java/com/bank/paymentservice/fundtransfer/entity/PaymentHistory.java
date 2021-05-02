package com.bank.paymentservice.fundtransfer.entity;

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

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.common.constants.ServiceType;

import lombok.Data;

@Data
@Entity
public class PaymentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String cif;

	private Long beneficiaryId;

	private String toCurrency;

	private BigDecimal paidAmount;
	
	private BigDecimal dueAmount;

	private String transactionRefNo;

	private String purpose;

	private String remarks;

	private LocalDateTime requestedAt;

	private String status;
	
	@Enumerated(EnumType.STRING)
	private ServiceType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private Account accountFrom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private Account accountTo;
}
