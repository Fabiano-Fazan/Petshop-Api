package com.petshop.api.service;

import com.petshop.api.dto.AnimalDTO;
import com.petshop.api.dto.CreateAnimalDTO;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.AnimalMapper;
import com.petshop.api.model.entities.Client;
import com.petshop.api.repository.AnimalRepository;
import com.petshop.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimalService {

    private  final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;
    private final AnimalMapper animalMapper;

    public Page<AnimalDTO> getAllAnimals(Pageable pageable){
        return animalRepository.findAll(pageable)
                .map(animalMapper::toDto);
    }
    public AnimalDTO createAnimal(CreateAnimalDTO createAnimalDTO){
        Client client = clientRepository.findById(createAnimalDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Animal animal = animalMapper.toEntity(createAnimalDTO);
        animal.setClient(client);

        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.toDto(savedAnimal);
    }

    public void deleteAnimal(UUID id){
        if(!animalRepository.existsById(id)){
            throw new RuntimeException("Animal not found");
        }
        animalRepository.deleteById(id);
    }
}
