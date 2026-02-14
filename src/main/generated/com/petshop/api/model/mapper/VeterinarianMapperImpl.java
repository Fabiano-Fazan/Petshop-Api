package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateVeterinarianDto;
import com.petshop.api.dto.response.VeterinarianResponseDto;
import com.petshop.api.dto.update.UpdateVeterinarianDto;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.entities.VeterinarianCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T22:36:55-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    @Override
    public Veterinarian toEntity(CreateVeterinarianDto dto) {
        if ( dto == null ) {
            return null;
        }

        Veterinarian.VeterinarianBuilder veterinarian = Veterinarian.builder();

        veterinarian.name( dto.getName() );
        veterinarian.crmv( dto.getCrmv() );
        veterinarian.phone( dto.getPhone() );
        veterinarian.email( dto.getEmail() );

        return veterinarian.build();
    }

    @Override
    public VeterinarianResponseDto toResponseDto(Veterinarian veterinarian) {
        if ( veterinarian == null ) {
            return null;
        }

        VeterinarianResponseDto veterinarianResponseDto = new VeterinarianResponseDto();

        veterinarianResponseDto.setCategory( veterinarianCategoryName( veterinarian ) );
        veterinarianResponseDto.setId( veterinarian.getId() );
        veterinarianResponseDto.setName( veterinarian.getName() );
        veterinarianResponseDto.setCrmv( veterinarian.getCrmv() );
        veterinarianResponseDto.setPhone( veterinarian.getPhone() );
        veterinarianResponseDto.setEmail( veterinarian.getEmail() );

        return veterinarianResponseDto;
    }

    @Override
    public void updateVeterinarianFromDto(UpdateVeterinarianDto updateVeterinarianDto, Veterinarian veterinarian) {
        if ( updateVeterinarianDto == null ) {
            return;
        }

        if ( updateVeterinarianDto.getName() != null ) {
            veterinarian.setName( updateVeterinarianDto.getName() );
        }
        if ( updateVeterinarianDto.getPhone() != null ) {
            veterinarian.setPhone( updateVeterinarianDto.getPhone() );
        }
        if ( updateVeterinarianDto.getEmail() != null ) {
            veterinarian.setEmail( updateVeterinarianDto.getEmail() );
        }
    }

    private String veterinarianCategoryName(Veterinarian veterinarian) {
        VeterinarianCategory category = veterinarian.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
