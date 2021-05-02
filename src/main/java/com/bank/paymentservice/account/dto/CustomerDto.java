package com.bank.paymentservice.account.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	private Long id;

	private String firstName;

	private String lastName;

	private String mobileNumber;

	private String email;

	private String cif;

	private LocalDate dateOfBirth;

	private String gender;

	private String branchCode;
	
	private String nationality;
	
	private String status;

	private List<AccountDto> accounts;

	private List<BeneficiaryDto> beneficiaries;
}
