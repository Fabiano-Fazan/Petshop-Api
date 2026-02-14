package com.petshop.api.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateAnimalDto {
    @Schema(description = "Animal name", example = "Rex")
    private String name;
    @Schema(description = "Animal species", example = "Dog")
    private String species;
    @Schema(description = "Animal breed", example = "Golden Retriever")
    private String breed;
    @Schema(description = "Animal birth date", example = "2020-01-01")
    private LocalDate birthDate;
}
