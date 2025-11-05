package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateClientDTO;
import com.petshop.api.dto.request.UpdateClientDTO;
import com.petshop.api.dto.response.AnimalResponseDTO;
import com.petshop.api.dto.response.ClientResponseDTO;
import com.petshop.api.model.entities.Address;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:30:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public Client toEntity(CreateClientDTO createClientDTO) {
        if ( createClientDTO == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.name( createClientDTO.getName() );
        client.phone( createClientDTO.getPhone() );
        client.cpf( createClientDTO.getCpf() );
        client.address( addressDataToAddress( createClientDTO.getAddress() ) );

        return client.build();
    }

    @Override
    public ClientResponseDTO toResponseDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();

        clientResponseDTO.setId( client.getId() );
        clientResponseDTO.setName( client.getName() );
        clientResponseDTO.setPhone( client.getPhone() );
        clientResponseDTO.setCpf( client.getCpf() );
        clientResponseDTO.setAddress( client.getAddress() );
        clientResponseDTO.setAnimals( animalListToAnimalResponseDTOList( client.getAnimals() ) );

        return clientResponseDTO;
    }

    @Override
    public void updateClientFromDTO(UpdateClientDTO updateClientDTO, Client client) {
        if ( updateClientDTO == null ) {
            return;
        }

        if ( updateClientDTO.getName() != null ) {
            client.setName( updateClientDTO.getName() );
        }
        if ( updateClientDTO.getPhone() != null ) {
            client.setPhone( updateClientDTO.getPhone() );
        }
    }

    protected Address addressDataToAddress(CreateClientDTO.AddressData addressData) {
        if ( addressData == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.street( addressData.getStreet() );
        address.city( addressData.getCity() );
        address.state( addressData.getState() );
        address.zipCode( addressData.getZipCode() );
        address.complement( addressData.getComplement() );

        return address.build();
    }

    protected List<AnimalResponseDTO> animalListToAnimalResponseDTOList(List<Animal> list) {
        if ( list == null ) {
            return null;
        }

        List<AnimalResponseDTO> list1 = new ArrayList<AnimalResponseDTO>( list.size() );
        for ( Animal animal : list ) {
            list1.add( animalMapper.toResponseDto( animal ) );
        }

        return list1;
    }
}
