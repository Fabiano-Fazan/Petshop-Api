package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.UpdateClientDTO;
import com.petshop.api.model.entities.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDTO(UpdateClientDTO.AddressData addressDataUpdate, @MappingTarget Address address);
}
