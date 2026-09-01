package com.petshop.api.service;

import com.petshop.api.domain.medicalAppointment.AppointmentRelationshipValidator;
import com.petshop.api.domain.medicalAppointment.AppointmentTimeCalculator;
import com.petshop.api.domain.medicalAppointment.AppointmentUpdater;
import com.petshop.api.domain.validator.ValidatorEntities;
import com.petshop.api.dto.request.CreateMedicalAppointmentDto;
import com.petshop.api.dto.update.UpdateMedicalAppointmentDto;
import com.petshop.api.dto.response.MedicalAppointmentResponseDto;
import com.petshop.api.exception.BusinessException;
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
    private final MedicalAppointmentMapper medicalAppointmentMapper;
    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final ClientRepository clientRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final ValidatorEntities validatorEntities;
    private final AppointmentTimeCalculator timeCalculator;
    private final AppointmentUpdater updaterAppointment;
    private final AppointmentRelationshipValidator relationshipValidator;


    public Page<MedicalAppointmentResponseDto> getAllMedicalAppointments(Pageable pageable) {
        return medicalAppointmentRepository.findAll(pageable)
                .map(medicalAppointmentMapper::toResponseDto);
    }

    public MedicalAppointmentResponseDto getMedicalAppointmentById(UUID id) {
        var medicalAppointment = validatorEntities.validate(id, medicalAppointmentRepository, "Medical Appointment");
        return medicalAppointmentMapper.toResponseDto(medicalAppointment);
    }

    public Page<MedicalAppointmentResponseDto> getMedicalAppointmentsByVeterinarianNameContainingIgnoreCase(String name, Pageable pageable){
        return medicalAppointmentRepository.findByVeterinarianNameContainingIgnoreCase(name, pageable)
                .map(medicalAppointmentMapper::toResponseDto);
    }

    public Page<MedicalAppointmentResponseDto> getMedicalAppointmentsByClientNameContainingIgnoreCase(String name, Pageable pageable){
        return medicalAppointmentRepository.findByClientNameContainingIgnoreCase(name, pageable)
                .map(medicalAppointmentMapper::toResponseDto);
    }

    @Transactional
    public MedicalAppointmentResponseDto createMedicalAppointment(
            CreateMedicalAppointmentDto dto
    ) {
        Client client = clientRepository.findWithLockById(dto.getClientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client not found")
                );

        Veterinarian veterinarian =
                veterinarianRepository.findWithLockById(
                        dto.getVeterinarianId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Veterinarian not found"
                        )
                );

        Animal animal = validatorEntities.validate(
                dto.getAnimalId(),
                animalRepository,
                "Animal"
        );

        relationshipValidator.validateAnimalBelongsToClient(animal, client);

        LocalDateTime start = dto.getAppointmentStartTime();
        int duration = timeCalculator.duration(
                dto.getDurationMinutes(),
                30
        );
        LocalDateTime end = timeCalculator.end(start, duration);

        timeCalculator.validateAppointmentTimeConflict(
                veterinarian.getId(),
                client.getId(),
                start,
                end
        );

        MedicalAppointment appointment =
                medicalAppointmentMapper.toEntity(dto);

        appointment.setClient(client);
        appointment.setAnimal(animal);
        appointment.setVeterinarian(veterinarian);
        appointment.setAppointmentEndTime(end);
        appointment.setDurationMinutes(duration);
        appointment.setAppointmentStatus(
                AppointmentStatus.SCHEDULED
        );

        return medicalAppointmentMapper.toResponseDto(
                medicalAppointmentRepository.save(appointment)
        );
    }

    @Transactional
    public MedicalAppointmentResponseDto updateMedicalAppointment(UUID id, UpdateMedicalAppointmentDto updateDto){
        var medicalAppointment = validatorEntities.validate(id, medicalAppointmentRepository, "Medical Appointment");
        UUID veterinarianId = updateDto.getVeterinarianId() != null
                ? updateDto.getVeterinarianId()
                : medicalAppointment.getVeterinarian().getId();
        veterinarianRepository.findWithLockById(veterinarianId)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found"));
        updaterAppointment.updateAppointment(medicalAppointment, updateDto);
        return medicalAppointmentMapper.toResponseDto(medicalAppointmentRepository.save(medicalAppointment));
    }

    @Transactional
    public void deleteMedicalAppointment(UUID id){
        CanDeleted(id);
        medicalAppointmentRepository.deleteById(id);
    }

    private void CanDeleted(UUID id) {
        MedicalAppointment appointment = validatorEntities.validate(id, medicalAppointmentRepository, "Medical Appointment");
        if (appointment.getAppointmentStatus() != AppointmentStatus.SCHEDULED) {
            throw new BusinessException("Only appointments with status SCHEDULED can be deleted");
        }
    }
}




