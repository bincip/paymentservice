package com.bank.paymentservice.fundtransfer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;
import com.bank.paymentservice.fundtransfer.repository.PaymentHistoryRepository;

@ExtendWith(MockitoExtension.class)
public class PaymentHistoryServiceImplTest {

	@InjectMocks
	PaymentHistoryServiceImpl paymentHistoryServiceImpl;

	@Mock
	PaymentHistoryRepository paymentHistoryRepository;
	
	@Test
	public void save() {
		paymentHistoryServiceImpl.save(getPaymentHistory());
		Mockito.verify(paymentHistoryRepository, atMostOnce()).save(getPaymentHistory());
	}

	@Test
	public void getNextTranscationReference() {
		LocalDateTime requestedAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
		StringBuilder txRefBuilder = new StringBuilder(requestedAt.format(formatter));
		String expTransactionref = txRefBuilder.append(1).toString();

		when(paymentHistoryRepository.getNextTransactionRefSeq()).thenReturn(1);
		String transactionref = paymentHistoryServiceImpl.getNextTranscationReference(requestedAt);

		assertEquals(expTransactionref, transactionref);
	}
	
	private PaymentHistory getPaymentHistory() {
		Account sourceAccount = getSoucrceAccount();
		Account targetAccount = getTargetAccount();
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setAccountFrom(sourceAccount);
		paymentHistory.setAccountTo(targetAccount);
		paymentHistory.setCif(sourceAccount.getCustomer().getCif());
		paymentHistory.setPaidAmount(fundTransferRequestDto.getAmount());
		paymentHistory.setTransactionRefNo("050220211");
		paymentHistory.setPurpose(fundTransferRequestDto.getPurposeCode());
		paymentHistory.setRemarks(fundTransferRequestDto.getPurposeDesc());
		paymentHistory.setType(fundTransferRequestDto.getServiceType());
		paymentHistory.setStatus("COMPLETED");
		return paymentHistory;
	}

	private Account getTargetAccount() {
		return TestUtils.getAccount("1234", "0001234", new BigDecimal(20000), "000");
	}

	private Account getSoucrceAccount() {
		return TestUtils.getAccount("4567", "0004567", new BigDecimal(20000), "000");
	}

}
