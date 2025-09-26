package com.petshop.api.dto;

import com.petshop.api.model.entities.Client;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateAnimalDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Species is required")
    private String species;

    private String breed;

    @NotBlank(message = "Birth date is required")
    private LocalDate birthDate;

    @NotBlank(message = "Client id is required")
    private UUID clientId;




}
