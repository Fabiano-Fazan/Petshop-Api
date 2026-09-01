package com.petshop.api.domain.medicalAppointment;

import com.petshop.api.domain.validator.ValidatorEntities;
import com.petshop.api.dto.update.UpdateMedicalAppointmentDto;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.MedicalAppointment;
import com.petshop.api.model.entities.Veterinarian;
import com.petshop.api.model.enums.AppointmentStatus;
import com.petshop.api.repository.AnimalRepository;
import com.petshop.api.repository.ClientRepository;
import com.petshop.api.repository.VeterinarianRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppointmentUpdater {

    private final ClientRepository clientRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final AnimalRepository animalRepository;
    private final ValidatorEntities validatorEntities;
    private final AppointmentTimeCalculator timeCalculator;

    private final AppointmentRelationshipValidator relationshipValidator;

    public void updateAppointment(
            MedicalAppointment appointment,
            @NonNull UpdateMedicalAppointmentDto updateDto
    ) {
        boolean clientChanged =
                updateDto.getClientId() != null;

        boolean animalChanged =
                updateDto.getAnimalId() != null;

        boolean veterinarianChanged =
                updateDto.getVeterinarianId() != null;

        boolean timeChanged =
                updateDto.getAppointmentStartTime() != null
                        || updateDto.getDurationMinutes() != null;

        Client finalClient = appointment.getClient();

        if (clientChanged) {
            finalClient = validatorEntities.validate(
                    updateDto.getClientId(),
                    clientRepository,
                    "Client"
            );
        }

        Animal finalAnimal = appointment.getAnimal();

        if (animalChanged) {
            finalAnimal = validatorEntities.validate(
                    updateDto.getAnimalId(),
                    animalRepository,
                    "Animal"
            );
        }

        Veterinarian finalVeterinarian =
                appointment.getVeterinarian();

        if (veterinarianChanged) {
            finalVeterinarian = validatorEntities.validate(
                    updateDto.getVeterinarianId(),
                    veterinarianRepository,
                    "Veterinarian"
            );
        }

        if (clientChanged || animalChanged) {
            relationshipValidator
                    .validateAnimalBelongsToClient(
                            finalAnimal,
                            finalClient
                    );
        }


        boolean isReactivating =
                updateDto.getAppointmentStatus()
                        == AppointmentStatus.SCHEDULED
                        && appointment.getAppointmentStatus()
                        != AppointmentStatus.SCHEDULED;

        boolean mustValidateConflict =
                timeChanged
                        || veterinarianChanged
                        || clientChanged
                        || isReactivating;

        if (mustValidateConflict) {
            LocalDateTime start = timeCalculator.start(
                    updateDto.getAppointmentStartTime(),
                    appointment.getAppointmentStartTime()
            );

            int duration = timeCalculator.duration(
                    updateDto.getDurationMinutes(),
                    appointment.getDurationMinutes()
            );

            LocalDateTime end = timeCalculator.end(
                    start,
                    duration
            );

            timeCalculator.validateConflict(
                    finalVeterinarian.getId(),
                    finalClient.getId(),
                    start,
                    end,
                    appointment.getId()
            );

            appointment.setAppointmentStartTime(start);
            appointment.setAppointmentEndTime(end);
            appointment.setDurationMinutes(duration);
        }

        if (clientChanged) {
            appointment.setClient(finalClient);
        }

        if (animalChanged) {
            appointment.setAnimal(finalAnimal);
        }

        if (veterinarianChanged) {
            appointment.setVeterinarian(finalVeterinarian);
        }

        if (updateDto.getAppointmentStatus() != null) {
            appointment.setAppointmentStatus(
                    updateDto.getAppointmentStatus()
            );
        }

        if (updateDto.getNotes() != null) {
            appointment.setNotes(updateDto.getNotes());
        }

        if (updateDto.getTreatment() != null) {
            appointment.setTreatment(
                    updateDto.getTreatment()
            );
        }

        if (updateDto.getDiagnosis() != null) {
            appointment.setDiagnosis(
                    updateDto.getDiagnosis()
            );
        }
    }
}