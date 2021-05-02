package com.bank.paymentservice.fundtransfer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.paymentservice.fundtransfer.dto.FundTransferRequestDto;
import com.bank.paymentservice.fundtransfer.dto.FundTransferResponseDto;
import com.bank.paymentservice.fundtransfer.service.FundTransferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Fund Transfer API" })
@RestController
@RequestMapping("/api/fund-transfer")
public class FundTransferController {

	@Autowired
	FundTransferService fundTransferService;

	@ApiOperation(value = "Transfer Funds between accounts", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully completed the transaction"),
			@ApiResponse(code = 404, message = "Not a valid CIF code"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@PostMapping("/")
	public ResponseEntity<FundTransferResponseDto> transferFunds(
			@RequestBody @Valid FundTransferRequestDto fundTransferRequestDto) {
		FundTransferResponseDto response = fundTransferService.transferFunds(fundTransferRequestDto);
		return ResponseEntity.ok().body(response);
	}

}
