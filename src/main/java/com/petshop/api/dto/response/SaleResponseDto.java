package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {

    @Schema(description = "Sale ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientId;
    @Schema(description = "Client name", example = "João Silva")
    private String clientName;
    @Schema(description = "Sale date", example = "2023-12-30T14:00:00")
    private LocalDateTime saleDate;
    @Schema(description = "Total value", example = "100.00")
    private BigDecimal totalValue;
    @Schema(description = "Notes", example = "Customer requested delivery")
    private String notes;
    @Schema(description = "List of products in the sale")
    private List<ProductSaleResponseDto> productSales;
}
