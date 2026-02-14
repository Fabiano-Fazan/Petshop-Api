package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponseDto {

    @Schema(description = "Animal ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Animal name", example = "Rex")
    private String name;
    @Schema(description = "Animal species", example = "Dog")
    private String species;
    @Schema(description = "Animal birth date", example = "2020-01-01")
    private LocalDate birthDate;
    @Schema(description = "Animal breed", example = "Golden Retriever")
    private String breed;
    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientId;
}
