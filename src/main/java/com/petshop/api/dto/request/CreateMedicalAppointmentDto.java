package com.petshop.api.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateMedicalAppointmentDto {

    @Schema(description = "Veterinarian ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Veterinarian ID is required")
    private UUID veterinarianId;

    @Schema(description = "Animal ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Animal ID is required")
    private UUID animalId;

    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Client ID ir required")
    private UUID clientId;

    @Schema(description = "Appointment start time", example = "2023-12-30T14:00:00")
    @NotNull(message = "Appointment date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentStartTime;

    @Schema(description = "Duration in minutes", example = "60")
    private Integer durationMinutes;

    @Schema(description = "Diagnosis", example = "Healthy animal")
    @Size(min = 5, max = 500, message = "The diagnosis must be between 5 and 500 characters")
    private String diagnosis;

    @Schema(description = "Notes", example = "Follow up in 6 months")
    private String notes;

}
