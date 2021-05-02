package com.bank.paymentservice.common.config;

/**
 * 
 * Exception handler
 * 
 * @author Bincy P
 *
 */

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.paymentservice.account.exception.AccountNotFoundException;
import com.bank.paymentservice.account.exception.CustomerNotFoundException;
import com.bank.paymentservice.common.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ AccountNotFoundException.class })
	public @ResponseBody ErrorDto handleAccountNotFoundException(AccountNotFoundException e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ CustomerNotFoundException.class })
	public @ResponseBody ErrorDto handleCustomerNotFoundException(CustomerNotFoundException e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ Exception.class })
	public @ResponseBody ErrorDto handleException(Exception e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	

}
