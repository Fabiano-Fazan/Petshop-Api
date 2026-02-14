package com.petshop.api.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDto {

    @Schema(description = "Street name", example = "Rua das Flores")
    @Size(min = 1, message = "Street cannot be empty")
    private String street;

    @Schema(description = "City name", example = "São Paulo")
    @Size(min = 1, message = "City cannot be empty")
    private String city;

    @Schema(description = "State name", example = "SP")
    @Size(min = 2, max = 2, message = "State must be 2 characters")
    private String state;

    @Schema(description = "Zip code", example = "12345-678")
    @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$",
            message = "ZIP code must be in the format XXXXX-XXX or XXXXXXXX")
    private String zipCode;

    @Schema(description = "Complement", example = "Apto 101")
    private String complement;

}
