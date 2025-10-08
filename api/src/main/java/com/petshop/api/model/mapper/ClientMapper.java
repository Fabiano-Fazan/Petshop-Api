package com.petshop.api.model.mapper;

import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import com.petshop.api.model.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(CreateClientDTO createClientDTO);
    ClientDTO toDto(Client client);

    void updateClientFromDTO(CreateClientDTO createClientDTO, @MappingTarget Client client);

}
