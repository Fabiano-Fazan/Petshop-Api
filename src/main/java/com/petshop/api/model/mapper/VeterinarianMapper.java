package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateVeterinarianDTO;
import com.petshop.api.dto.request.UpdateVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.model.entities.Veterinarian;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VeterinarianMapper {

    Veterinarian toEntity(CreateVeterinarianDTO createVeterinarianDTO);

    VeterinarianResponseDTO toResponseDto(Veterinarian veterinarian);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVeterinarianFromDTO(UpdateVeterinarianDTO updateVeterinarianDTO, @MappingTarget Veterinarian veterinarian);
}
