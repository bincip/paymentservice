package com.bank.paymentservice.fundtransfer.service.impl;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;

import java.util.EnumMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.common.constants.ServiceType;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.strategy.FundTransferStrategy;
import com.bank.paymentservice.fundtransfer.strategy.IntraBankTransferStrategy;
import com.bank.paymentservice.fundtransfer.strategy.OwnAccountTransferStrategy;

@ExtendWith(MockitoExtension.class)
public class FundTransferServiceImplTest {

	@InjectMocks
	FundTransferServiceImpl fundTransferService;

	@Mock
	OwnAccountTransferStrategy ownAccountTransferStrategy;

	@Mock
	IntraBankTransferStrategy intraBankTransferStrategy;

	private EnumMap<ServiceType, FundTransferStrategy> fundTransferStrategies;

	@BeforeEach
	public void setUp() {
		fundTransferStrategies = new EnumMap<>(ServiceType.class);
		fundTransferStrategies.put(ServiceType.OAT, ownAccountTransferStrategy);
		fundTransferStrategies.put(ServiceType.IBT, intraBankTransferStrategy);

		ReflectionTestUtils.setField(fundTransferService, "fundTransferStrategies", fundTransferStrategies);
	}

	@Test
	public void transferFunds_when_own_account() {
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		fundTransferRequestDto.setServiceType(ServiceType.OAT);
		fundTransferService.transferFunds(fundTransferRequestDto);
		Mockito.verify(ownAccountTransferStrategy, atMostOnce()).transferFunds(fundTransferRequestDto);
	}

	@Test
	public void transferFunds_when_own_account_failure() {
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		fundTransferRequestDto.setServiceType(ServiceType.OAT);
		fundTransferService.transferFunds(fundTransferRequestDto);
		Mockito.verify(intraBankTransferStrategy, never()).transferFunds(fundTransferRequestDto);
	}

	@Test
	public void transferFunds_intra_bank_account() {
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		fundTransferRequestDto.setServiceType(ServiceType.IBT);
		fundTransferService.transferFunds(fundTransferRequestDto);
		Mockito.verify(intraBankTransferStrategy, atMostOnce()).transferFunds(fundTransferRequestDto);
	}

	@Test
	public void transferFunds_intra_bank_account_failure() {
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		fundTransferRequestDto.setServiceType(ServiceType.IBT);
		fundTransferService.transferFunds(fundTransferRequestDto);
		Mockito.verify(ownAccountTransferStrategy, never()).transferFunds(fundTransferRequestDto);
	}

}
