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
    date = "2025-10-31T13:56:13-0300",
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

        medicalAppointment.veterinarian( createMedicalAppointmentDTOToVeterinarian( createMedicalAppointmentDTO ) );
        medicalAppointment.client( createMedicalAppointmentDTOToClient( createMedicalAppointmentDTO ) );
        medicalAppointment.animal( createMedicalAppointmentDTOToAnimal( createMedicalAppointmentDTO ) );
        medicalAppointment.notes( createMedicalAppointmentDTO.getNotes() );
        medicalAppointment.reason( createMedicalAppointmentDTO.getReason() );

        medicalAppointment.status( AppointmentStatus.SCHEDULED );

        return medicalAppointment.build();
    }

    @Override
    public MedicalAppointmentResponseDTO toResponseDto(MedicalAppointment medicalAppointment) {
        if ( medicalAppointment == null ) {
            return null;
        }

        MedicalAppointmentResponseDTO medicalAppointmentResponseDTO = new MedicalAppointmentResponseDTO();

        medicalAppointmentResponseDTO.setVeterinarianId( medicalAppointmentVeterinarianId( medicalAppointment ) );
        medicalAppointmentResponseDTO.setClientId( medicalAppointmentClientId( medicalAppointment ) );
        medicalAppointmentResponseDTO.setAnimalId( medicalAppointmentAnimalId( medicalAppointment ) );
        if ( medicalAppointment.getStatus() != null ) {
            medicalAppointmentResponseDTO.setStatus( medicalAppointment.getStatus().name() );
        }
        medicalAppointmentResponseDTO.setId( medicalAppointment.getId() );
        medicalAppointmentResponseDTO.setReason( medicalAppointment.getReason() );
        medicalAppointmentResponseDTO.setDiagnosis( medicalAppointment.getDiagnosis() );
        medicalAppointmentResponseDTO.setTreatment( medicalAppointment.getTreatment() );
        medicalAppointmentResponseDTO.setNotes( medicalAppointment.getNotes() );

        return medicalAppointmentResponseDTO;
    }

    protected Veterinarian createMedicalAppointmentDTOToVeterinarian(CreateMedicalAppointmentDTO createMedicalAppointmentDTO) {
        if ( createMedicalAppointmentDTO == null ) {
            return null;
        }

        Veterinarian.VeterinarianBuilder veterinarian = Veterinarian.builder();

        veterinarian.id( createMedicalAppointmentDTO.getVeterinarianId() );

        return veterinarian.build();
    }

    protected Client createMedicalAppointmentDTOToClient(CreateMedicalAppointmentDTO createMedicalAppointmentDTO) {
        if ( createMedicalAppointmentDTO == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( createMedicalAppointmentDTO.getClientId() );

        return client.build();
    }

    protected Animal createMedicalAppointmentDTOToAnimal(CreateMedicalAppointmentDTO createMedicalAppointmentDTO) {
        if ( createMedicalAppointmentDTO == null ) {
            return null;
        }

        Animal.AnimalBuilder animal = Animal.builder();

        animal.id( createMedicalAppointmentDTO.getAnimalId() );

        return animal.build();
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
