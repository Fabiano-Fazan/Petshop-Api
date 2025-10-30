package com.petshop.api.service;

import com.petshop.api.dto.request.CreateVeterinarianDTO;
import com.petshop.api.dto.request.UpdateVeterinarianDTO;
import com.petshop.api.dto.response.VeterinarianResponseDTO;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.mapper.VeterinarianMapper;
import com.petshop.api.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianMapper veterinarianMapper;

    public Page<VeterinarianResponseDTO> getAllVeterinarians(Pageable pageable){
        return veterinarianRepository.findAll(pageable)
        .map(veterinarianMapper::toResponseDto);
    }

    public VeterinarianResponseDTO getVeterinarianById(UUID id){
        return veterinarianRepository.findById(id)
                .map(veterinarianMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with id: " + id));
    }

    public Page<VeterinarianResponseDTO> getVeterinarianByNameContainingIgnoreCase(String name, Pageable pageable){
        return veterinarianRepository.findAllByNameContainingIgnoreCase(name,pageable)
                .map(veterinarianMapper::toResponseDto);
    }

    @Transactional
    public VeterinarianResponseDTO createVeterinarian(CreateVeterinarianDTO createVeterinarianDTO){
        Veterinarian veterinarian = veterinarianMapper.toEntity(createVeterinarianDTO);
        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);
        return veterinarianMapper.toResponseDto(savedVeterinarian);
    }

    @Transactional
    public VeterinarianResponseDTO updateVeterinarian(UUID id, UpdateVeterinarianDTO updateVeterinarianDTO){
        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with ID: " + id));
                veterinarianMapper.updateVeterinarianFromDTO(updateVeterinarianDTO, veterinarian);
                veterinarianRepository.save(veterinarian);
                return veterinarianMapper.toResponseDto(veterinarian);
    }


    @Transactional
    public void deleteVeterinarian(UUID id){
        if(!veterinarianRepository.existsById(id)){
            throw new ResourceNotFoundException("Veterinarian not found with ID: " + id);
            }
        veterinarianRepository.deleteById(id);
    }
}
