package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressDto {
    @Schema(description = "Street name", example = "Rua das Flores, 123")
    @NotBlank(message = "Street is required")
    private String street;

    @Schema(description = "City name", example = "São Paulo")
    @NotBlank(message = "City is required")
    private String city;

    @Schema(description = "State name", example = "SP")
    @NotBlank(message = "State is required")
    private String state;

    @Schema(description = "Zip code", example = "12345-678")
    @NotBlank(message = "ZipCode is required")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "ZipCode must follow the format XXXXX-XXX")
    private String zipCode;

    @Schema(description = "Complement", example = "Apto 101")
    private String complement;
}
