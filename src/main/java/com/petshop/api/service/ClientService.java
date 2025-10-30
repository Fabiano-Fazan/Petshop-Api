package com.petshop.api.service;

import com.petshop.api.dto.request.UpdateClientDTO;
import com.petshop.api.dto.response.ClientResponseDTO;
import com.petshop.api.dto.request.CreateClientDTO;
import com.petshop.api.exception.CpfAlreadyExistsException;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Address;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.mapper.AddressMapper;
import com.petshop.api.model.mapper.ClientMapper;
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
    private final AddressMapper addressMapper;


    public Page<ClientResponseDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toResponseDto);
    }

    public ClientResponseDTO getClientById(UUID id) {
        return clientRepository.findById(id)
                .map(clientMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    public Page<ClientResponseDTO> getClientByNameContainingIgnoreCase(String name, Pageable pageable) {
        return clientRepository.findAllByNameContainingIgnoreCase(name,pageable)
                .map(clientMapper::toResponseDto);
    }

    @Transactional
    public ClientResponseDTO createClient(CreateClientDTO createClientDTO) {
        if (clientRepository.existsByCpf(createClientDTO.getCpf())){
            throw new CpfAlreadyExistsException("This CPF already exists");
        }
        Client client = clientMapper.toEntity(createClientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponseDto(savedClient);
    }

    @Transactional
    public ClientResponseDTO updateClient(UUID id, UpdateClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
        clientMapper.updateClientFromDTO(clientDTO, client);
        if (clientDTO.getAddress() != null) {
            if (client.getAddress() == null) {
                client.setAddress(new Address());
            }
            addressMapper.updateAddressFromDTO(clientDTO.getAddress(), client.getAddress());
        }
        clientRepository.save(client);
        return clientMapper.toResponseDto(client);
    }

    @Transactional
    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }
        clientRepository.deleteById(id);
    }
}
