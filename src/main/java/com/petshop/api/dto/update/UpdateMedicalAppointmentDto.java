package com.petshop.api.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petshop.api.model.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateMedicalAppointmentDto {

    @Schema(description = "Veterinarian ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID veterinarianId;
    @Schema(description = "Animal ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID animalId;
    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientId;

    @Schema(description = "Appointment start time", example = "2023-12-30T14:00:00")
    @Future(message = "Appointment date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentStartTime;

    @Schema(description = "Duration in minutes", example = "60")
    @Positive(message = "Duration must be greater than zero")
    private Integer durationMinutes;

    @Schema(description = "Appointment status", example = "SCHEDULED")
    private AppointmentStatus appointmentStatus;

    @Schema(description = "Diagnosis", example = "Healthy animal")
    @Size(min = 5, max = 500, message = "The diagnosis must be between 5 and 500 characters")
    private String diagnosis;

    @Schema(description = "Treatment", example = "Vaccination")
    @Size(min = 5, max = 700, message = "The treatment must be between 5 and 700 characters")
    private String treatment;

    @Schema(description = "Notes", example = "Follow up in 6 months")
    private String notes;
}
