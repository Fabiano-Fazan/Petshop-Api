package com.petshop.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateFinancialPaymentDto {

    @Schema(description = "Paid amount", example = "50.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price needs to be positive")
    private BigDecimal paidAmount;

    @Schema(description = "Payment date", example = "2023-12-30")
    @NotNull(message = "The payment date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Schema(description = "Monetary type ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "The monetary type ID cannot be null")
    private UUID monetaryTypeId;

    @Schema(description = "Next due date", example = "2024-01-30")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextDueDate;

    @Schema(description = "Notes", example = "Partial payment")
    private String notes;
}
