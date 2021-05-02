package com.bank.paymentservice.fundtransfer.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.bank.paymentservice.common.constants.ServiceType;

import lombok.Data;

@Data
public class FundTransferRequestDto {

    @NotBlank(message = "From Account Number cannot be empty")
    private String fromAccount;

    @NotBlank(message = "To Account Number cannot be empty")
    private String toAccount;

    @NotBlank(message = "Amount cannot be empty")
    private BigDecimal amount;

    @NotBlank(message = "Service Type cannot be empty")
    private ServiceType serviceType;

    private String currency;

    private String purposeCode;

    private String purposeDesc;

    private String chargeBearer;

    private long beneficiaryId;

    private String otp;
    
    private BigDecimal dealRate;

}
