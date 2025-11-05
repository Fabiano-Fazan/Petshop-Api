package com.petshop.api.service;

import com.petshop.api.dto.request.CreateMedicalAppointmentDTO;
import com.petshop.api.dto.response.MedicalAppointmentResponseDTO;
import com.petshop.api.exception.AppointmentDateTimeAlreadyExistsException;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.MedicalAppointment;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.enums.AppointmentStatus;
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

import java.time.LocalDateTime;
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

        LocalDateTime start = createMedicalAppointmentDTO.getAppointmentStartTime();
        int duration = createMedicalAppointmentDTO.getDurationMinutes() != null ? createMedicalAppointmentDTO.getDurationMinutes() : 30;
        LocalDateTime end = start.plusMinutes(duration);

        boolean hasConflict = medicalAppointmentRepository.existsConflictingAppointment(createMedicalAppointmentDTO.getVeterinarianId(), start, end);
        if (hasConflict) {
            throw new AppointmentDateTimeAlreadyExistsException("This time slot is already booked for the veterinarian.");
        }

        Client client = clientRepository.findById(createMedicalAppointmentDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + createMedicalAppointmentDTO.getClientId()));

        Animal animal = animalRepository.findById(createMedicalAppointmentDTO.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with ID: " + createMedicalAppointmentDTO.getAnimalId()));

        Veterinarian veterinarian = veterinarianRepository.findById(createMedicalAppointmentDTO.getVeterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with ID: " + createMedicalAppointmentDTO.getVeterinarianId()));

        MedicalAppointment appointment = medicalAppointmentMapper.toEntity(createMedicalAppointmentDTO);
        appointment.setAppointmentEndTime(end);
        appointment.setClient(client);
        appointment.setAnimal(animal);
        appointment.setVeterinarian(veterinarian);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        MedicalAppointment savedAppointment = medicalAppointmentRepository.save(appointment);
        return medicalAppointmentMapper.toResponseDto(savedAppointment);
    }

}





