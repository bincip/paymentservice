package com.bank.paymentservice.fundtransfer.strategy;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.service.AccountService;
import com.bank.paymentservice.common.constants.FundTransferErrorCode;
import com.bank.paymentservice.common.util.DateHelper;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;
import com.bank.paymentservice.fundtransfer.entity.PaymentHistory;
import com.bank.paymentservice.fundtransfer.exception.FundTransferException;
import com.bank.paymentservice.fundtransfer.service.PaymentHistoryService;

@Service
public class IntraBankTransferStrategy implements FundTransferStrategy {

	@Autowired
	AccountService accountService;

	@Autowired
	PaymentHistoryService paymentHistoryService;

	@Autowired
	DateHelper dateHelper;

	@Override
	public FundTransferResponseDto transferFunds(FundTransferRequestDto fundTransferRequestDto) {

		Account sourceAccount = accountService.getAccountByAccNumber(fundTransferRequestDto.getFromAccount());
		Account targetAccount = getTargetAccount(fundTransferRequestDto.getToAccount(), fundTransferRequestDto.getBeneficiaryId());
		
		validateTransaction(fundTransferRequestDto, sourceAccount, targetAccount);
		
		sourceAccount.setTransactionBlocked(true);
		accountService.save(sourceAccount);
		
		LocalDateTime requestedAt = dateHelper.getCurrentTime();
		String transactionRef = paymentHistoryService.getNextTranscationReference(requestedAt);
		
		sourceAccount.setAccountBalance(sourceAccount.getAccountBalance().subtract(fundTransferRequestDto.getAmount()));
		sourceAccount.setUpdatedOn(requestedAt);
		sourceAccount.setTransactionBlocked(false);
		accountService.save(sourceAccount);

		targetAccount.setAccountBalance(targetAccount.getAccountBalance().add(fundTransferRequestDto.getAmount()));
		targetAccount.setUpdatedOn(requestedAt);
		accountService.save(targetAccount);

		savePaymentHistory(fundTransferRequestDto, sourceAccount, targetAccount, transactionRef);

		FundTransferResponseDto response = new FundTransferResponseDto();
		response.setAccountBalance(sourceAccount.getAccountBalance());
		response.setAccountTo(targetAccount.getAccountNumber());
		response.setPaidAmount(fundTransferRequestDto.getAmount());
		response.setTransactionRefNo(transactionRef);
		response.setStatus("SUCCESS");
		
		return response;
	}

	private void savePaymentHistory(FundTransferRequestDto fundTransferRequestDto, Account sourceAccount,
			Account targetAccount, String transactionRef) {
		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setAccountFrom(sourceAccount);
		paymentHistory.setAccountTo(targetAccount);
		paymentHistory.setCif(sourceAccount.getCustomer().getCif());
		paymentHistory.setPaidAmount(fundTransferRequestDto.getAmount());
		paymentHistory.setTransactionRefNo(transactionRef);
		paymentHistory.setPurpose(fundTransferRequestDto.getPurposeCode());
		paymentHistory.setRemarks(fundTransferRequestDto.getPurposeDesc());
		paymentHistory.setType(fundTransferRequestDto.getServiceType());
		paymentHistory.setBeneficiaryId(fundTransferRequestDto.getBeneficiaryId());
		paymentHistory.setStatus("COMPLETED");

		paymentHistoryService.save(paymentHistory);
	}

	private void validateTransaction(FundTransferRequestDto fundTransferRequestDto, Account sourceAccount, Account targetAccount) {
		
		if (sourceAccount.isTransactionBlocked()) {
			throw new FundTransferException(FundTransferErrorCode.FT_4003.toString(),
					" Not able to complete the transaction at the moment ");
		}

		if (sourceAccount.getAccountBalance().compareTo(fundTransferRequestDto.getAmount())  < 0) {
			throw new FundTransferException(FundTransferErrorCode.FT_4001.toString(),
					" Insufficient balance in source account ");
		}
	}

	private Account getTargetAccount(String accountNumber, long beneficiaryId) {
		/** TODO: If account number is not available, get account by beneficiaryId */
		return accountService.getAccountByAccNumber(accountNumber);
	}

}
