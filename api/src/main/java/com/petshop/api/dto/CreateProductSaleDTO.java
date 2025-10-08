package com.petshop.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class CreateProductSaleDTO {
    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
