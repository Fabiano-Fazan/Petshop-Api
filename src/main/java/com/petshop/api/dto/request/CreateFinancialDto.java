package com.petshop.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateFinancialDto {

    @Schema(description = "Description of the financial record", example = "Payment for services")
    @NotBlank(message = "Description is required")
    private String description;

    @Schema(description = "Amount", example = "150.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price needs to be positive")
    private BigDecimal amount;

    @Schema(description = "Due date", example = "2023-12-31")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Schema(description = "Payment date", example = "2023-12-30")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Schema(description = "Number of installments", example = "1")
    @NotNull(message = "The installment cannot be null")
    private Integer installments;

    @Schema(description = "Interval in days between installments", example = "30")
    private Integer intervalDays;

    @Schema(description = "Is paid", example = "true")
    private Boolean isPaid;

    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Client ID is required")
    private UUID clientId;

    @Schema(description = "Notes", example = "Paid via bank transfer")
    private String notes;

}
