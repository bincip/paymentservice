package com.bank.paymentservice.fundtransfer.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PaymentHistoryRepositoryTest  {
	
	@Autowired
	PaymentHistoryRepository paymentHistoryRepository;

	@Test
	public void savePaymentHistoryTest() {
		PaymentHistory paymentHistory = paymentHistoryRepository.save(getPaymentHistory());
		assertNotNull(paymentHistory.getId());
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
		Account account = TestUtils.getAccount("1234", "0001234", new BigDecimal(20000), "000");
		account.setId((long) 1);
		return account;
	}

	private Account getSoucrceAccount() {
		Account account = TestUtils.getAccount("4567", "0004567", new BigDecimal(20000), "000");
		account.setId((long) 2);
		return account;
	}

}
