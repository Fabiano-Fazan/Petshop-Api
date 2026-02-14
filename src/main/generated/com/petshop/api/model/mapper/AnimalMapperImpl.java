package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateAnimalDto;
import com.petshop.api.dto.response.AnimalResponseDto;
import com.petshop.api.dto.update.UpdateAnimalDto;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T22:36:55-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public Animal toEntity(CreateAnimalDto dto) {
        if ( dto == null ) {
            return null;
        }

        Animal.AnimalBuilder animal = Animal.builder();

        animal.name( dto.getName() );
        animal.species( dto.getSpecies() );
        animal.breed( dto.getBreed() );
        animal.birthDate( dto.getBirthDate() );

        return animal.build();
    }

    @Override
    public AnimalResponseDto toResponseDto(Animal animal) {
        if ( animal == null ) {
            return null;
        }

        AnimalResponseDto animalResponseDto = new AnimalResponseDto();

        animalResponseDto.setClientId( animalClientId( animal ) );
        animalResponseDto.setId( animal.getId() );
        animalResponseDto.setName( animal.getName() );
        animalResponseDto.setSpecies( animal.getSpecies() );
        animalResponseDto.setBirthDate( animal.getBirthDate() );
        animalResponseDto.setBreed( animal.getBreed() );

        return animalResponseDto;
    }

    @Override
    public void updateAnimalFromDto(UpdateAnimalDto updateAnimalDto, Animal animal) {
        if ( updateAnimalDto == null ) {
            return;
        }

        if ( updateAnimalDto.getName() != null ) {
            animal.setName( updateAnimalDto.getName() );
        }
        if ( updateAnimalDto.getSpecies() != null ) {
            animal.setSpecies( updateAnimalDto.getSpecies() );
        }
        if ( updateAnimalDto.getBreed() != null ) {
            animal.setBreed( updateAnimalDto.getBreed() );
        }
        if ( updateAnimalDto.getBirthDate() != null ) {
            animal.setBirthDate( updateAnimalDto.getBirthDate() );
        }
    }

    private UUID animalClientId(Animal animal) {
        Client client = animal.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }
}
