package com.bank.paymentservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Accounts Service API" })
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@ApiOperation(value = "Gets all the accounts of the customer By Account Number", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved the accounts"),
			@ApiResponse(code = 404, message = "Not a valid CIF code"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@GetMapping("/by-account-number/{accountNumber}")
	public ResponseEntity<AccountDto> getAccountsByAccNumber(@PathVariable String accountNumber) {
		AccountDto account = accountService.getAccountDetailsByAccNumber(accountNumber);
		return ResponseEntity.ok().body(account);
	}

	@ApiOperation(value = "Gets all the accounts of the customer By IBAN", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved the accounts"),
			@ApiResponse(code = 404, message = "Not a valid CIF code"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@GetMapping("/by-iban/{iban}")
	public ResponseEntity<AccountDto> getAccountsByIban(@PathVariable String iban) {
		AccountDto account = accountService.getAccountDetailsByIban(iban);
		return ResponseEntity.ok().body(account);
	}
}
