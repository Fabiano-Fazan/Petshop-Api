package com.petshop.api.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Product name", example = "Dog Food")
    private String name;
    @Schema(description = "Product description", example = "Premium dog food for adult dogs")
    private String description;
    @Schema(description = "Product price", example = "50.00")
    private BigDecimal price;
    @Schema(description = "Category name", example = "Food")
    private String category;
    @Schema(description = "Quantity in stock", example = "100")
    private Integer quantityInStock;
}
