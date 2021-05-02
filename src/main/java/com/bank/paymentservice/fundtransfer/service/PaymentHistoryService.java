package com.bank.paymentservice.fundtransfer.service;

import java.time.LocalDateTime;

import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;

public interface PaymentHistoryService {

	void save(PaymentHistory paymentHistory);
	
	String getNextTranscationReference(LocalDateTime requestedAt);
}
