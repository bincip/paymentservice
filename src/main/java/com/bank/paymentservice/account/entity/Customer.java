package com.bank.paymentservice.account.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private LocalDateTime createdOn;
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts;
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Beneficiary> beneficiaries;

}
