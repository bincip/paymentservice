package com.bank.paymentservice.fundtransfer.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;
import com.bank.paymentservice.fundtransfer.repository.PaymentHistoryRepository;
import com.bank.paymentservice.fundtransfer.service.PaymentHistoryService;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

	@Autowired
	PaymentHistoryRepository paymentHistoryRepository;

	@Override
	public void save(PaymentHistory paymentHistory) {
		paymentHistoryRepository.save(paymentHistory);
	}

	@Override
	public String getNextTranscationReference(LocalDateTime requestedAt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
		StringBuilder txRefBuilder = new StringBuilder(requestedAt.format(formatter));
		txRefBuilder.append(paymentHistoryRepository.getNextTransactionRefSeq());
		return txRefBuilder.toString();
	}

}
