package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreatedVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.model.entities.Veterinarian;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinarianMapper {

    Veterinarian toEntity(CreatedVeterinarianDTO createdVeterinarianDTO);
    VeterinarianResponseDTO toResponseDto(Veterinarian veterinarian);

}
