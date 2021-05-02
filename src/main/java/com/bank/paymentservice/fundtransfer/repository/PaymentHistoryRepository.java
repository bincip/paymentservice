package com.bank.paymentservice.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
	
	@Query(value = "SELECT NEXT VALUE FOR TXN_REFERENCE_SEQ", nativeQuery = true)
	public int getNextTransactionRefSeq();
}
