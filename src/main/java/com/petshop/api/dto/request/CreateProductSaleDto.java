package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateProductSaleDto {

    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Product ID is required")
    private UUID productId;

    @Schema(description = "Quantity of the product", example = "2")
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Schema(description = "Price of the product", example = "10.50")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price needs to be positive")
    private BigDecimal price;
}
