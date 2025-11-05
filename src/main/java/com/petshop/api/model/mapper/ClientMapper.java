package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.UpdateClientDTO;
import com.petshop.api.dto.response.ClientResponseDTO;
import com.petshop.api.dto.request.CreateClientDTO;
import com.petshop.api.model.entities.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {AnimalMapper.class})
public interface ClientMapper {

    Client toEntity(CreateClientDTO createClientDTO);

    @Mapping(target = "id", ignore = true)
    ClientResponseDTO toResponseDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "address", ignore = true)
    void updateClientFromDTO(UpdateClientDTO updateClientDTO, @MappingTarget Client client);
}
