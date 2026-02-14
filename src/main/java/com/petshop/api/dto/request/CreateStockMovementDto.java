package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateStockMovementDto {

    @Schema(description = "Quantity", example = "10")
    @NotNull(message = "The quantity cannot be null")
    @Positive(message = "The quantity needs to be positive")
    private Integer quantity;

    @Schema(description = "Description", example = "Stock replenishment")
    @NotBlank(message = "The description cannot be null")
    private String description;

    @Schema(description = "Invoice number", example = "INV-12345")
    private String invoice;

    @Schema(description = "Price", example = "100.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price needs to be positive")
    private BigDecimal price;
}
