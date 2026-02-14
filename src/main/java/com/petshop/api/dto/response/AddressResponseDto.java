package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    @Schema(description = "Street name", example = "Rua das Flores")
    private String street;
    @Schema(description = "City name", example = "São Paulo")
    private String city;
    @Schema(description = "State name", example = "SP")
    private String state;
    @Schema(description = "Zip code", example = "12345-678")
    private String zipCode;
    @Schema(description = "Complement", example = "Apto 101")
    private String complement;
}
