package com.petshop.api.model.mapper;

import com.petshop.api.dto.AnimalDTO;
import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import com.petshop.api.model.entities.Address;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-07T21:08:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(CreateClientDTO createClientDTO) {
        if ( createClientDTO == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.name( createClientDTO.getName() );
        client.phone( createClientDTO.getPhone() );
        client.address( addressDataToAddress( createClientDTO.getAddress() ) );
        client.animals( animalDTOListToAnimalList( createClientDTO.getAnimals() ) );

        return client.build();
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
                client.setAddress( Address.builder().build() );
            }
            addressDataToAddress1( createClientDTO.getAddress(), client.getAddress() );
        }
        else {
            client.setAddress( null );
        }
        if ( client.getAnimals() != null ) {
            List<Animal> list = animalDTOListToAnimalList( createClientDTO.getAnimals() );
            if ( list != null ) {
                client.getAnimals().clear();
                client.getAnimals().addAll( list );
            }
            else {
                client.setAnimals( null );
            }
        }
        else {
            List<Animal> list = animalDTOListToAnimalList( createClientDTO.getAnimals() );
            if ( list != null ) {
                client.setAnimals( list );
            }
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

    protected Animal animalDTOToAnimal(AnimalDTO animalDTO) {
        if ( animalDTO == null ) {
            return null;
        }

        Animal.AnimalBuilder animal = Animal.builder();

        animal.id( animalDTO.getId() );
        animal.name( animalDTO.getName() );
        animal.species( animalDTO.getSpecies() );
        animal.breed( animalDTO.getBreed() );
        animal.birthDate( animalDTO.getBirthDate() );

        return animal.build();
    }

    protected List<Animal> animalDTOListToAnimalList(List<AnimalDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Animal> list1 = new ArrayList<Animal>( list.size() );
        for ( AnimalDTO animalDTO : list ) {
            list1.add( animalDTOToAnimal( animalDTO ) );
        }

        return list1;
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
