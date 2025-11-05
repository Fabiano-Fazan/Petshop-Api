package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateVeterinarianDTO;
import com.petshop.api.dto.request.UpdateVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.model.entities.Veterinarian;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:30:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    @Override
    public Veterinarian toEntity(CreateVeterinarianDTO createVeterinarianDTO) {
        if ( createVeterinarianDTO == null ) {
            return null;
        }

        Veterinarian.VeterinarianBuilder veterinarian = Veterinarian.builder();

        veterinarian.name( createVeterinarianDTO.getName() );
        veterinarian.crmv( createVeterinarianDTO.getCrmv() );
        veterinarian.phone( createVeterinarianDTO.getPhone() );
        veterinarian.veterinarianCategory( createVeterinarianDTO.getVeterinarianCategory() );
        veterinarian.email( createVeterinarianDTO.getEmail() );

        return veterinarian.build();
    }

    @Override
    public VeterinarianResponseDTO toResponseDto(Veterinarian veterinarian) {
        if ( veterinarian == null ) {
            return null;
        }

        VeterinarianResponseDTO veterinarianResponseDTO = new VeterinarianResponseDTO();

        veterinarianResponseDTO.setId( veterinarian.getId() );
        veterinarianResponseDTO.setName( veterinarian.getName() );
        veterinarianResponseDTO.setCrmv( veterinarian.getCrmv() );
        veterinarianResponseDTO.setPhone( veterinarian.getPhone() );
        if ( veterinarian.getVeterinarianCategory() != null ) {
            veterinarianResponseDTO.setVeterinarianCategory( veterinarian.getVeterinarianCategory().name() );
        }
        veterinarianResponseDTO.setEmail( veterinarian.getEmail() );

        return veterinarianResponseDTO;
    }

    @Override
    public void updateVeterinarianFromDTO(UpdateVeterinarianDTO updateVeterinarianDTO, Veterinarian veterinarian) {
        if ( updateVeterinarianDTO == null ) {
            return;
        }

        if ( updateVeterinarianDTO.getName() != null ) {
            veterinarian.setName( updateVeterinarianDTO.getName() );
        }
        if ( updateVeterinarianDTO.getPhone() != null ) {
            veterinarian.setPhone( updateVeterinarianDTO.getPhone() );
        }
        if ( updateVeterinarianDTO.getEmail() != null ) {
            veterinarian.setEmail( updateVeterinarianDTO.getEmail() );
        }
    }
}
