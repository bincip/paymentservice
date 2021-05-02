package com.bank.paymentservice.fundtransfer.service;

import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;

public interface FundTransferService {

	FundTransferResponseDto transferFunds(FundTransferRequestDto fundTransferRequestDto);

}
