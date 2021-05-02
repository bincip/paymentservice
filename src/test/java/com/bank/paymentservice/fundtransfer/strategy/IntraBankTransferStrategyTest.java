package com.bank.paymentservice.fundtransfer.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.service.AccountService;
import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.common.constants.ServiceType;
import com.bank.paymentservice.common.util.DateHelper;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;
import com.bank.paymentservice.fundtransfer.exception.FundTransferException;
import com.bank.paymentservice.fundtransfer.service.PaymentHistoryService;

@ExtendWith(MockitoExtension.class)
public class IntraBankTransferStrategyTest {

	@InjectMocks
	IntraBankTransferStrategy intraBankTransferStrategy;

	@Mock
	AccountService accountService;

	@Mock
	PaymentHistoryService paymentHistoryService;

	@Mock
	DateHelper dateHelper;

	private final String SOURCE_ACC_NUMBER = "1234";

	private final String TARGET_ACC_NUMBER = "5678";
	
	private final String SOURCE_IBAN = "IBAN1234";

	private final String TARGET_IBAN = "IBAN5678";

	private final String SOURCE_CIF = "0001234";

	private final String TARGET_CIF = "0005678";

	@Test
	public void transferFunds_when_valid() {
		LocalDateTime requestedAt = LocalDateTime.now();

		when(accountService.getAccountByAccNumber(SOURCE_ACC_NUMBER)).thenReturn(getSoucrceAccountValid());
		when(accountService.getAccountByAccNumber(TARGET_ACC_NUMBER)).thenReturn(getTargetAccountValid());
		when(dateHelper.getCurrentTime()).thenReturn(requestedAt);
		when(paymentHistoryService.getNextTranscationReference(requestedAt)).thenReturn("050220211");
		
		FundTransferResponseDto response = intraBankTransferStrategy.transferFunds(getFundTransferRequest());
		
		assertNotNull(response);
		assertEquals(new BigDecimal(15000), response.getAccountBalance());
		assertEquals(new BigDecimal(5000), response.getPaidAmount());
		assertEquals(TARGET_ACC_NUMBER, response.getAccountTo());
		assertEquals("050220211", response.getTransactionRefNo());
		assertEquals("SUCCESS", response.getStatus());

	}

	@Test
	public void transferFunds_when_transaction_blocked() {
		when(accountService.getAccountByAccNumber(SOURCE_ACC_NUMBER)).thenReturn(getSoucrceAccountBlocked());
		when(accountService.getAccountByAccNumber(TARGET_ACC_NUMBER)).thenReturn(getTargetAccountValid());
	
		assertThrows(FundTransferException.class, () -> {
			intraBankTransferStrategy.transferFunds(getFundTransferRequest());;
		});
	}

	@Test
	public void transferFunds_when_insufficientBalance() {
		when(accountService.getAccountByAccNumber(SOURCE_ACC_NUMBER)).thenReturn(getSoucrceAccountLowBalance());
		when(accountService.getAccountByAccNumber(TARGET_ACC_NUMBER)).thenReturn(getTargetAccountValid());
	
		assertThrows(FundTransferException.class, () -> {
			intraBankTransferStrategy.transferFunds(getFundTransferRequest());;
		});
	}

	private Account getSoucrceAccountValid() {
		 return TestUtils.getAccount(SOURCE_ACC_NUMBER, SOURCE_IBAN, new BigDecimal(20000), SOURCE_CIF);
	}

	private Account getSoucrceAccountLowBalance() {
		return TestUtils.getAccount(SOURCE_ACC_NUMBER, SOURCE_IBAN, new BigDecimal(4000), SOURCE_CIF);
	}

	private Account getSoucrceAccountBlocked() {
		Account account = TestUtils.getAccount(SOURCE_ACC_NUMBER, SOURCE_IBAN, new BigDecimal(20000), SOURCE_CIF);
		account.setTransactionBlocked(true);
		return account;
	}
	
	private Account getTargetAccountValid() {
		 return TestUtils.getAccount(TARGET_ACC_NUMBER, TARGET_IBAN, new BigDecimal(10000), TARGET_CIF);
	}
	
	private FundTransferRequestDto getFundTransferRequest() {
		FundTransferRequestDto request = new FundTransferRequestDto();
		request.setFromAccount(SOURCE_ACC_NUMBER);
		request.setToAccount(TARGET_ACC_NUMBER);
		request.setServiceType(ServiceType.IBT);
		request.setPurposeCode("FAMILY_SUPPORT");
		request.setPurposeDesc("Family Support");
		request.setAmount(new BigDecimal(5000));
		return request;
	}

}
