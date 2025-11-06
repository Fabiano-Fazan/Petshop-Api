package com.petshop.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class CreateAnimalDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Species is required")
    private String species;

    private String breed;

    private LocalDate birthDate;

    @NotNull(message = "Client ID is required")
    private UUID clientId;
}
