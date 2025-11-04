package com.petshop.api.service;

import com.petshop.api.dto.request.CreateMedicalAppointmentDTO;
import com.petshop.api.dto.response.MedicalAppointmentResponseDTO;
import com.petshop.api.exception.AppointmentDateTimeAlreadyExistsException;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.MedicalAppointment;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.mapper.MedicalAppointmentMapper;
import com.petshop.api.repository.AnimalRepository;
import com.petshop.api.repository.ClientRepository;
import com.petshop.api.repository.MedicalAppointmentRepository;
import com.petshop.api.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalAppointmentService {
    private final ClientRepository clientRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final MedicalAppointmentMapper medicalAppointmentMapper;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public Page<MedicalAppointmentResponseDTO> getAllMedicalAppointments(Pageable pageable) {
        return medicalAppointmentRepository.findAll(pageable)
                .map(medicalAppointmentMapper::toResponseDto);
    }

    public MedicalAppointmentResponseDTO getMedicalAppointmentById(UUID id) {
        MedicalAppointment medicalAppointmentById = medicalAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical appointment not found with ID: " + id));
        return medicalAppointmentMapper.toResponseDto(medicalAppointmentById);

    }

    @Transactional
    public MedicalAppointmentResponseDTO createMedicalAppointment(CreateMedicalAppointmentDTO createMedicalAppointmentDTO) {
        if (medicalAppointmentRepository.existsByAppointmentDateTime(createMedicalAppointmentDTO.getAppointmentDate())){
            throw  new AppointmentDateTimeAlreadyExistsException("An appointment has already been scheduled for that date.");
        }
        Client client = clientRepository.findById(createMedicalAppointmentDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + createMedicalAppointmentDTO.getClientId()));

        Animal animal = animalRepository.findById(createMedicalAppointmentDTO.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with ID: " + createMedicalAppointmentDTO.getAnimalId()));

        Veterinarian veterinarian = veterinarianRepository.findById(createMedicalAppointmentDTO.getVeterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with ID: " + createMedicalAppointmentDTO.getVeterinarianId()));

    }

}





