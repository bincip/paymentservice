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

    private BigDecimal amount;

    private ServiceType serviceType;

    private String currency;

    private String purposeCode;

    private String purposeDesc;

    private String chargeBearer;

    private long beneficiaryId;

    private String otp;
    
    private BigDecimal dealRate;

}
