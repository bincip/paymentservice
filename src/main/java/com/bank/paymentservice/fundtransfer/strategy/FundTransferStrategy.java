package com.bank.paymentservice.fundtransfer.strategy;

import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;

public interface FundTransferStrategy {
	
	FundTransferResponseDto transferFunds(FundTransferRequestDto fundTransferRequestDto);

}
