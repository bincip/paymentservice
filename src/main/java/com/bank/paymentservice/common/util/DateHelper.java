package com.bank.paymentservice.common.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DateHelper {
	
	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
}
