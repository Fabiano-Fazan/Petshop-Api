package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialPaymentResponseDto {
    @Schema(description = "The unique identifier of the financial payment")
    private UUID id;
    @Schema(description = "The amount paid")
    private BigDecimal paidAmount;
    @Schema(description = "The date of the payment")
    private LocalDate paymentDate;
    @Schema(description = "The unique identifier of the monetary type")
    private UUID monetaryTypeId;
    @Schema(description = "The name of the monetary type")
    private String monetaryTypeName;
    @Schema(description = "Notes about the financial payment")
    private String notes;
}

