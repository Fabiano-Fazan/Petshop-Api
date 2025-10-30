package com.petshop.api.service;

import com.petshop.api.dto.response.AnimalResponseDTO;
import com.petshop.api.dto.request.CreateAnimalDTO;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.mapper.AnimalMapper;
import com.petshop.api.model.entities.Client;
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
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;
    private final AnimalMapper animalMapper;

    public Page<AnimalResponseDTO> getAllAnimals(Pageable pageable){
        return animalRepository.findAll(pageable)
                .map(animalMapper::toResponseDto);
    }

    @Transactional
    public AnimalResponseDTO createAnimal(CreateAnimalDTO createAnimalDTO){
        Client client = clientRepository.findById(createAnimalDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + createAnimalDTO.getClientId()));

        Animal animal = animalMapper.toEntity(createAnimalDTO);
        animal.setClient(client);

        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.toResponseDto(savedAnimal);
    }

    @Transactional
    public void deleteAnimal(UUID id){
        if(!animalRepository.existsById(id)){
            throw new ResourceNotFoundException("Animal not found with ID: " + id);
        }
        animalRepository.deleteById(id);
    }
}
