package com.petshop.api.service;

import com.petshop.api.dto.AnimalDTO;
import com.petshop.api.dto.CreateAnimalDTO;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.repository.AnimalRepository;
import com.petshop.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimalService {

    private  AnimalRepository animalRepository;
    private ClientRepository clientRepository;

    public Page<AnimalDTO> getAllAnimals(Pageable pageable){
        return animalRepository.findAll(pageable)
                .map(this::convertToAnimalDTO);

    }
    public AnimalDTO createAnimal(CreateAnimalDTO createAnimalDTO){
        Client client = clientRepository.findById(createAnimalDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + createAnimalDTO.getClientId()));
        Animal animal = new Animal();
        animal.setName(createAnimalDTO.getName());
        animal.setBirthDate(createAnimalDTO.getBirthDate());
        animal.setBreed(createAnimalDTO.getBreed());
        animal.setSpecies(createAnimalDTO.getSpecies());
        animal.setClient(client);

        Animal savedAnimal = animalRepository.save(animal);
        return convertToAnimalDTO(savedAnimal);

    }

    public void deleteAnimal(UUID id){
        if(!animalRepository.existsById(id)){
            throw new RuntimeException("Animal not found");
        }
        animalRepository.deleteById(id);
    }

    private AnimalDTO convertToAnimalDTO(Animal animal){
        return new AnimalDTO(
                animal.getId(),
                animal.getName(),
                animal.getSpecies(),
                animal.getBirthDate(),
                animal.getBreed(),
                animal.getClient().getId()
        );
    }
}
