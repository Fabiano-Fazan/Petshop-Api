package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonetaryTypeResponseDto {
    @Schema(description = "Monetary type ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    @Schema(description = "Monetary type name", example = "Credit Card")
    private String name;
    @Schema(description = "Description", example = "Payment via credit card")
    private String description;
}
