package com.bank.paymentservice.common.constants;

import lombok.Getter;

public enum ServiceType {
	OAT("Own Account Transfer"),
	IBT("Intra Bank Transfer"),
	IT("International Transfer");
	
	@Getter
	private String value;
	
	private ServiceType(String value) {
		this.value = value;
	}
}
