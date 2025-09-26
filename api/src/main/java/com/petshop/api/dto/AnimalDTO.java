package com.petshop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private UUID id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private String breed;

}
