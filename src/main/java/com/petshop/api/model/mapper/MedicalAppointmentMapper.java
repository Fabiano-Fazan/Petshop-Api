package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateMedicalAppointmentDTO;
import com.petshop.api.dto.response.MedicalAppointmentResponseDTO;
import com.petshop.api.model.entities.MedicalAppointment;
import org.mapstruct.Mapping;

public interface MedicalAppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "veterinarianId", target = "veterinarian.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "animalId", target = "animal.id")
    @Mapping(target = "status", constant = "SCHEDULED")
    MedicalAppointment toEntity(CreateMedicalAppointmentDTO createMedicalAppointmentDTO);

    @Mapping(source = "veterinarian.id", target = "veterinarianId")
    @Mapping(source = "veterinarian.name", target = "veterinarianName")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "animal.id", target = "animalId")
    @Mapping(source = "animal.name", target = "animalName")
    @Mapping(source = "status", target = "status")
    MedicalAppointmentResponseDTO toResponseDto(MedicalAppointment medicalAppointment);
}



