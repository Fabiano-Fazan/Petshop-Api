package com.petshop.api.model.entities;

import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(CreateClientDTO createClientDTO);
    ClientDTO toDto(Client client);
    void updateClientFromDTO(CreateClientDTO createClientDTO, @MappingTarget Client client);

}
