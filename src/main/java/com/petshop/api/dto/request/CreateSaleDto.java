package com.petshop.api.dto.request;

import com.petshop.api.model.enums.SalePaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateSaleDto {

    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Client ID is required")
    private UUID clientId;

    @Schema(description = "List of products in the sale")
    @NotEmpty(message = "Product sales list is required")
    @Valid
    private List<CreateProductSaleDto> productSales;

    @Schema(description = "Number of installments", example = "1")
    @Min(value = 1, message = "The sale must have at least one installment.")
    private Integer installments;

    @Schema(description = "Interval in days between installments", example = "30")
    @Min(value = 0, message = "The interval must be positive")
    private Integer intervalDays;

    @Schema(description = "Payment type", example = "INSTALLMENTS")
    @NotNull(message = "Payment type is required")
    private SalePaymentType paymentType;

    @Schema(description = "Notes about the sale", example = "Customer requested delivery")
    private String notes;

}
