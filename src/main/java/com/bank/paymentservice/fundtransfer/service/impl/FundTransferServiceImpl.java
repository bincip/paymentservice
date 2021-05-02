package com.bank.paymentservice.fundtransfer.service.impl;

import java.util.EnumMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.paymentservice.common.constants.ServiceType;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;
import com.bank.paymentservice.fundtransfer.service.FundTransferService;
import com.bank.paymentservice.fundtransfer.strategy.FundTransferStrategy;
import com.bank.paymentservice.fundtransfer.strategy.IntraBankTransferStrategy;
import com.bank.paymentservice.fundtransfer.strategy.OwnAccountTransferStrategy;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	OwnAccountTransferStrategy ownAccountTransferStrategy;

	@Autowired
	IntraBankTransferStrategy intraBankTransferStrategy;

	private EnumMap<ServiceType, FundTransferStrategy> fundTransferStrategies;

	@PostConstruct
	public void init() {
		fundTransferStrategies = new EnumMap<>(ServiceType.class);
		fundTransferStrategies.put(ServiceType.OAT, ownAccountTransferStrategy);
		fundTransferStrategies.put(ServiceType.IBT, intraBankTransferStrategy);
	}

	@Override
	public FundTransferResponseDto transferFunds(FundTransferRequestDto fundTransferRequestDto) {
		FundTransferStrategy strategy = fundTransferStrategies.get(fundTransferRequestDto.getServiceType());
		return strategy.transferFunds(fundTransferRequestDto);
	}

}
