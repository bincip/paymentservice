package com.bank.paymentservice.fundtransfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bank.paymentservice.account.util.TestUtils;
import com.bank.paymentservice.common.constants.ServiceType;
import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;
import com.bank.paymentservice.fundtransfer.service.FundTransferService;

@ExtendWith(MockitoExtension.class)
public class FundTransferControllerTest {
	
	@InjectMocks
	FundTransferController fundTransferController;
	
	@Mock
	FundTransferService fundTransferService;

	@Test
	public void transferFundsTest() {
		FundTransferRequestDto fundTransferRequestDto = TestUtils.getFundTransferRequestDto();
		
		when(fundTransferService.transferFunds(fundTransferRequestDto)).thenReturn(TestUtils.getFundTransferResponseDto());
		ResponseEntity<FundTransferResponseDto> response = fundTransferController.transferFunds(fundTransferRequestDto);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
