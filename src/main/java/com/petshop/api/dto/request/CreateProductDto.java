package com.petshop.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateProductDto {

    @Schema(description = "Product name", example = "Dog Food")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @Schema(description = "Product description", example = "Premium dog food for adult dogs")
    @Size(min = 10, max = 255, message = "The description must be between 10 and 255 characters")
    private String description;

    @Schema(description = "Product price", example = "50.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price needs to be positive")
    private BigDecimal price;

    @Schema(description = "Category ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    @Schema(description = "Quantity in stock", example = "100")
    @PositiveOrZero(message = "The quantity in stock needs to be positive or zero")
    private Integer quantityInStock;
}
