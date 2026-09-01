package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialResponseDto {

    @Schema(description = "Financial record ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Description", example = "Payment for services")
    private String description;
    @Schema(description = "Amount", example = "150.00")
    private BigDecimal amount;
    @Schema(description = "Due date", example = "2023-12-31")
    private LocalDate dueDate;
    @Schema(description = "Payment date", example = "2023-12-30")
    private LocalDate paymentDate;
    @Schema(description = "Installment number", example = "1")
    private Integer installment;
    @Schema(description = "Is paid", example = "true")
    private Boolean isPaid;
    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientId;
    @Schema(description = "Client name", example = "João Silva")
    private String name;
    @Schema(description = "Sale ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID saleId;
    @Schema(description = "Notes", example = "Paid via bank transfer")
    private String notes;
    @Schema(description = "Balance", example = "150.00")
    private BigDecimal balance;
    @Schema(description = "Date created", example = "2023-12-30")
    private LocalDate dateCreated;
    @Schema(description = "Payments")
    private List<FinancialPaymentResponseDto> payments;
}
