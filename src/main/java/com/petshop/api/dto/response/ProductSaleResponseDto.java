package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleResponseDto {

    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID productId;
    @Schema(description = "Product name", example = "Dog Food")
    private String productName;
    @Schema(description = "Quantity", example = "2")
    private Integer quantity;
    @Schema(description = "Unit price", example = "50.00")
    private BigDecimal unitPrice;
}
