package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreatedVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.model.entities.Veterinarian;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T21:14:39-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    @Override
    public Veterinarian toEntity(CreatedVeterinarianDTO createdVeterinarianDTO) {
        if ( createdVeterinarianDTO == null ) {
            return null;
        }

        Veterinarian.VeterinarianBuilder veterinarian = Veterinarian.builder();

        veterinarian.name( createdVeterinarianDTO.getName() );
        veterinarian.crmv( createdVeterinarianDTO.getCrmv() );
        veterinarian.phone( createdVeterinarianDTO.getPhone() );
        veterinarian.veterinarianCategory( createdVeterinarianDTO.getVeterinarianCategory() );
        veterinarian.email( createdVeterinarianDTO.getEmail() );

        return veterinarian.build();
    }

    @Override
    public VeterinarianResponseDTO toResponseDto(Veterinarian veterinarian) {
        if ( veterinarian == null ) {
            return null;
        }

        VeterinarianResponseDTO veterinarianResponseDTO = new VeterinarianResponseDTO();

        veterinarianResponseDTO.setName( veterinarian.getName() );
        veterinarianResponseDTO.setCrmv( veterinarian.getCrmv() );
        veterinarianResponseDTO.setPhone( veterinarian.getPhone() );
        veterinarianResponseDTO.setVeterinarianCategory( veterinarian.getVeterinarianCategory() );
        veterinarianResponseDTO.setEmail( veterinarian.getEmail() );

        return veterinarianResponseDTO;
    }
}
