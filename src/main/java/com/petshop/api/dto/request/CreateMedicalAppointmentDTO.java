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
    private LocalDateTime appointmentDate;

    @Size(min = 10, max = 500, message = "The reason must be between 10 and 500 characters")
    private String reason;

    @Size(min = 10, max = 700, message = "The notes must be between 10 and 700 characters")
    private String notes;
}

