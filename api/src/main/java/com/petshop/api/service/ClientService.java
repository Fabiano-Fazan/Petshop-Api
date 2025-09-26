package com.petshop.api.service;

import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import com.petshop.api.model.entities.Client;
import com.petshop.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    private AnimalService animalService;


    public Page<ClientDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(this::convertToClientDTO);

    }

    public ClientDTO createClient(CreateClientDTO createClientDTO) {
        Client client = new Client();

    }

    private ClientDTO convertToClientDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getPhone(),
                client.getAdress(),
                client.getAnimals()
        );
    }
}
