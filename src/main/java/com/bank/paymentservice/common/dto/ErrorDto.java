package com.bank.paymentservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorDto {
	
	private String code;
	private String message;
	private String errorlogId;
}
