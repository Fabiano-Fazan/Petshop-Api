package com.petshop.api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponseDTO {
    private UUID id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private String breed;
    private UUID clientId;
}
