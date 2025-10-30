package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateVeterinarianDTO;
import com.petshop.api.dto.request.UpdateVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.service.VeterinarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/veterinarians")
public class VeterinarianController {
    private final VeterinarianService veterinarianService;

    @GetMapping
    public ResponseEntity<Page<VeterinarianResponseDTO>> getAllVeterinarians(Pageable pageable){
        Page<VeterinarianResponseDTO> allVeterinarians = veterinarianService.getAllVeterinarians(pageable);
        return ResponseEntity.ok(allVeterinarians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDTO> getVeterinarianById(@PathVariable UUID id){
        VeterinarianResponseDTO veterinarianById = veterinarianService.getVeterinarianById(id);
        return ResponseEntity.ok(veterinarianById);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<VeterinarianResponseDTO>> getVeterinarianByName(@PathVariable String name, Pageable pageable){
        Page<VeterinarianResponseDTO> veterinarianByName = veterinarianService.getVeterinarianByNameContainingIgnoreCase(name,pageable);
        return ResponseEntity.ok(veterinarianByName);
    }

    @PostMapping
    public ResponseEntity<VeterinarianResponseDTO> createVeterinarian(@Valid @RequestBody CreateVeterinarianDTO createVeterinarianDTO){
        VeterinarianResponseDTO createdVeterinarian = veterinarianService.createVeterinarian(createVeterinarianDTO);
        return new ResponseEntity<>(createdVeterinarian, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(@PathVariable UUID id, @Valid @RequestBody UpdateVeterinarianDTO updateVeterinarianDTO){
        VeterinarianResponseDTO updatedVeterinarian = veterinarianService.updateVeterinarian(id, updateVeterinarianDTO);
        return new ResponseEntity<>(updatedVeterinarian, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable UUID id){
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }


}
