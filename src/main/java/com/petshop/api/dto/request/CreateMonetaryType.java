package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMonetaryType {

    @Schema(description = "Monetary type name", example = "Credit Card")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Description", example = "Payment via credit card")
    private String description;
}
