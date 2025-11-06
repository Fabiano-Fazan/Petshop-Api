package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.UpdateClientDto;
import com.petshop.api.model.entities.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDTO(UpdateClientDto.AddressData addressDataUpdate, @MappingTarget Address address);
}
