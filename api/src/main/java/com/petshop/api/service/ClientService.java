package com.petshop.api.service;

import com.petshop.api.dto.ClientDTO;
import com.petshop.api.dto.CreateClientDTO;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.mapper.ClientMapper;
import com.petshop.api.repository.AnimalRepository;
import com.petshop.api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AnimalRepository animalRepository;

    public Page<ClientDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toDto);
    }

    public ClientDTO getClientById(UUID id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    public Page<ClientDTO> getClientByNameContainingIgnoreCase(String name, Pageable pageable) {
        return clientRepository.findAllByNameContainingIgnoreCase(name,pageable)
                .map(clientMapper::toDto);
    }

    public ClientDTO createClient(CreateClientDTO createClientDTO) {
        Client client = clientMapper.toEntity(createClientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    public ClientDTO updateClient(UUID id, CreateClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientMapper.updateClientFromDTO(clientDTO, client);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDto(updatedClient);
    }

    @Transactional
    public void deleteClient(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        animalRepository.deleteAll(client.getAnimals());
        clientRepository.delete(client);
    }
}
