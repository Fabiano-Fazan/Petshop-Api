package com.petshop.api.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateMedicalAppointmentDTO {

    @NotNull(message = "Veterinarian ID is required")
    private UUID veterinarianId;

    @NotNull(message = "Animal ID is required")
    private UUID animalId;

    @NotNull(message = "Client ID ir required")
    private UUID clientId;

    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointmentStartTime;

    private Integer durationMinutes;

    @Size(min = 10, max = 500, message = "The diagnosis must be between 10 and 500 characters")
    private String diagnosis;

    @Size(min = 10, max = 700, message = "The treatment must be between 10 and 700 characters")
    private String treatment;
}

