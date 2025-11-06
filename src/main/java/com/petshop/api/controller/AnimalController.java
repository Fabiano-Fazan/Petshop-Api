package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateAnimalDto;
import com.petshop.api.dto.response.AnimalResponseDto;
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
    public ResponseEntity<Page<AnimalResponseDto>> getAllAnimals(Pageable pageable) {
        Page<AnimalResponseDto> animals = animalService.getAllAnimals(pageable);
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDto> createAnimal(@Valid @RequestBody CreateAnimalDto animalDTO) {
        AnimalResponseDto createdAnimal = animalService.createAnimal(animalDTO);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}