package com.petshop.api.model.mapper;

import com.petshop.api.dto.AnimalDTO;
import com.petshop.api.dto.CreateAnimalDTO;
import com.petshop.api.model.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    Animal toEntity(CreateAnimalDTO createAnimalDTO);

    @Mapping(source = "client.id", target = "clientId")
    AnimalDTO toDto(Animal animal);
}
