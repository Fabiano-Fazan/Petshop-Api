package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateAnimalDTO;
import com.petshop.api.dto.response.AnimalResponseDTO;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T12:59:01-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public Animal toEntity(CreateAnimalDTO createAnimalDTO) {
        if ( createAnimalDTO == null ) {
            return null;
        }

        Animal.AnimalBuilder animal = Animal.builder();

        animal.name( createAnimalDTO.getName() );
        animal.species( createAnimalDTO.getSpecies() );
        animal.breed( createAnimalDTO.getBreed() );
        animal.birthDate( createAnimalDTO.getBirthDate() );

        return animal.build();
    }

    @Override
    public AnimalResponseDTO toResponseDto(Animal animal) {
        if ( animal == null ) {
            return null;
        }

        AnimalResponseDTO animalResponseDTO = new AnimalResponseDTO();

        animalResponseDTO.setClientId( animalClientId( animal ) );
        animalResponseDTO.setId( animal.getId() );
        animalResponseDTO.setName( animal.getName() );
        animalResponseDTO.setSpecies( animal.getSpecies() );
        animalResponseDTO.setBirthDate( animal.getBirthDate() );
        animalResponseDTO.setBreed( animal.getBreed() );

        return animalResponseDTO;
    }

    private UUID animalClientId(Animal animal) {
        if ( animal == null ) {
            return null;
        }
        Client client = animal.getClient();
        if ( client == null ) {
            return null;
        }
        UUID id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
