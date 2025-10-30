package com.petshop.api.model.mapper;

import com.petshop.api.dto.response.AnimalResponseDTO;
import com.petshop.api.dto.request.CreateAnimalDTO;
import com.petshop.api.model.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    Animal toEntity(CreateAnimalDTO createAnimalDTO);

    @Mapping(source = "client.id", target = "clientId")
    AnimalResponseDTO toResponseDto(Animal animal);
}
