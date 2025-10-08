package com.petshop.api.controller;

import com.petshop.api.dto.AnimalDTO;
import com.petshop.api.dto.CreateAnimalDTO;
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
    public ResponseEntity<Page<AnimalDTO>> getAllAnimals(Pageable pageable) {
        Page<AnimalDTO> animals = animalService.getAllAnimals(pageable);
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody CreateAnimalDTO animalDTO) {
        AnimalDTO createdAnimal = animalService.createAnimal(animalDTO);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
