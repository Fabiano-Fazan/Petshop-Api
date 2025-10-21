package com.petshop.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateStockMoviment {

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "The quantity cannot be null")
    @Positive(message = "The quantity needs to be positive")
    private Integer quantity;

    @NotBlank(message = "The description cannot be null")
    private String description;
}
