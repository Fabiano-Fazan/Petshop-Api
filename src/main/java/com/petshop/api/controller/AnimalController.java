package com.petshop.api.controller;

import com.petshop.api.dto.response.AnimalResponseDTO;
import com.petshop.api.dto.request.CreateAnimalDTO;
import com.petshop.api.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<Page<AnimalResponseDTO>> getAllAnimals(Pageable pageable) {
        Page<AnimalResponseDTO> animals = animalService.getAllAnimals(pageable);
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(@Valid @RequestBody CreateAnimalDTO animalDTO) {
        AnimalResponseDTO createdAnimal = animalService.createAnimal(animalDTO);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}