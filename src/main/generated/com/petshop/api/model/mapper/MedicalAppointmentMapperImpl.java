package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateMedicalAppointmentDTO;
import com.petshop.api.dto.response.MedicalAppointmentResponseDTO;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.MedicalAppointment;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.enums.AppointmentStatus;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T08:22:26-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class MedicalAppointmentMapperImpl implements MedicalAppointmentMapper {

    @Override
    public MedicalAppointment toEntity(CreateMedicalAppointmentDTO createMedicalAppointmentDTO) {
        if ( createMedicalAppointmentDTO == null ) {
            return null;
        }

        MedicalAppointment.MedicalAppointmentBuilder medicalAppointment = MedicalAppointment.builder();

        medicalAppointment.diagnosis( createMedicalAppointmentDTO.getDiagnosis() );
        medicalAppointment.treatment( createMedicalAppointmentDTO.getTreatment() );
        medicalAppointment.appointmentStartTime( createMedicalAppointmentDTO.getAppointmentStartTime() );
        medicalAppointment.durationMinutes( createMedicalAppointmentDTO.getDurationMinutes() );

        medicalAppointment.status( AppointmentStatus.SCHEDULED );

        return medicalAppointment.build();
    }

    @Override
    public MedicalAppointmentResponseDTO toResponseDto(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }

        MedicalAppointmentResponseDTO medicalAppointmentResponseDTO = new MedicalAppointmentResponseDTO();

        medicalAppointmentResponseDTO.setVeterinarianName( medicalAppointmentVeterinarianName( medicalAppointment ) );
        medicalAppointmentResponseDTO.setVeterinarianId( medicalAppointmentVeterinarianId( medicalAppointment ) );
        medicalAppointmentResponseDTO.setClientName( medicalAppointmentClientName( medicalAppointment ) );
        medicalAppointmentResponseDTO.setClientId( medicalAppointmentClientId( medicalAppointment ) );
        medicalAppointmentResponseDTO.setAnimalName( medicalAppointmentAnimalName( medicalAppointment ) );
        medicalAppointmentResponseDTO.setAnimalId( medicalAppointmentAnimalId( medicalAppointment ) );
        medicalAppointmentResponseDTO.setId( medicalAppointment.getId() );
        medicalAppointmentResponseDTO.setAppointmentStartTime( medicalAppointment.getAppointmentStartTime() );
        medicalAppointmentResponseDTO.setAppointmentEndTime( medicalAppointment.getAppointmentEndTime() );
        if ( medicalAppointment.getStatus() != null ) {
            medicalAppointmentResponseDTO.setStatus( medicalAppointment.getStatus().name() );
        }
        medicalAppointmentResponseDTO.setDiagnosis( medicalAppointment.getDiagnosis() );
        medicalAppointmentResponseDTO.setTreatment( medicalAppointment.getTreatment() );

        return medicalAppointmentResponseDTO;
    }

    private String medicalAppointmentVeterinarianName(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Veterinarian veterinarian = medicalAppointment.getVeterinarian();
        if ( veterinarian == null ) {
            return null;
        }
        String name = veterinarian.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID medicalAppointmentVeterinarianId(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Veterinarian veterinarian = medicalAppointment.getVeterinarian();
        if ( veterinarian == null ) {
            return null;
        }
        UUID id = veterinarian.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String medicalAppointmentClientName(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Client client = medicalAppointment.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID medicalAppointmentClientId(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Client client = medicalAppointment.getClient();
        if ( client == null ) {
            return null;
        }
        UUID id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String medicalAppointmentAnimalName(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Animal animal = medicalAppointment.getAnimal();
        if ( animal == null ) {
            return null;
        }
        String name = animal.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID medicalAppointmentAnimalId(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }
        Animal animal = medicalAppointment.getAnimal();
        if ( animal == null ) {
            return null;
        }
        UUID id = animal.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
