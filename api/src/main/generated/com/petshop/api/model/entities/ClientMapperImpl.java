package com.petshop.api.model.entities;

import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-30T21:08:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(CreateClientDTO createClientDTO) {
        if ( createClientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( createClientDTO.getName() );
        client.setPhone( createClientDTO.getPhone() );
        client.setAddress( addressDataToAddress( createClientDTO.getAddress() ) );

        return client;
    }

    @Override
    public ClientDTO toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId( client.getId() );
        clientDTO.setName( client.getName() );
        clientDTO.setPhone( client.getPhone() );
        clientDTO.setAddress( client.getAddress() );
        List<Animal> list = client.getAnimals();
        if ( list != null ) {
            clientDTO.setAnimals( new ArrayList<Animal>( list ) );
        }

        return clientDTO;
    }

    @Override
    public void updateClientFromDTO(CreateClientDTO createClientDTO, Client client) {
        if ( createClientDTO == null ) {
            return;
        }

        client.setName( createClientDTO.getName() );
        client.setPhone( createClientDTO.getPhone() );
        if ( createClientDTO.getAddress() != null ) {
            if ( client.getAddress() == null ) {
                client.setAddress( new Address() );
            }
            addressDataToAddress1( createClientDTO.getAddress(), client.getAddress() );
        }
        else {
            client.setAddress( null );
        }
    }

    protected Address addressDataToAddress(CreateClientDTO.AddressData addressData) {
        if ( addressData == null ) {
            return null;
        }

        Address address = new Address();

        address.setStreet( addressData.getStreet() );
        address.setCity( addressData.getCity() );
        address.setState( addressData.getState() );
        address.setZipCode( addressData.getZipCode() );
        address.setComplement( addressData.getComplement() );

        return address;
    }

    protected void addressDataToAddress1(CreateClientDTO.AddressData addressData, Address mappingTarget) {
        if ( addressData == null ) {
            return;
        }

        mappingTarget.setStreet( addressData.getStreet() );
        mappingTarget.setCity( addressData.getCity() );
        mappingTarget.setState( addressData.getState() );
        mappingTarget.setZipCode( addressData.getZipCode() );
        mappingTarget.setComplement( addressData.getComplement() );
    }
}
