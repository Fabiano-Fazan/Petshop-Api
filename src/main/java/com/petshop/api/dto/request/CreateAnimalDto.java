package com.petshop.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class CreateAnimalDto {

    @Schema(description = "Animal name", example = "Rex")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Animal species", example = "Dog")
    @NotBlank(message = "Species is required")
    private String species;

    @Schema(description = "Animal breed", example = "Golden Retriever")
    private String breed;

    @Schema(description = "Animal birth date", example = "2020-01-01")
    @PastOrPresent(message = "Birth date must be in the past or present")
    private LocalDate birthDate;

    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Client ID is required")
    private UUID clientId;
}
