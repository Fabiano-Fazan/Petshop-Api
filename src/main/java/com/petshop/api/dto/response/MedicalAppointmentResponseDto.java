package com.petshop.api.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointmentResponseDto {

    @Schema(description = "Appointment ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Appointment start time", example = "2023-12-30T14:00:00")
    private LocalDateTime appointmentStartTime;
    @Schema(description = "Appointment end time", example = "2023-12-30T15:00:00")
    private LocalDateTime appointmentEndTime;
    @Schema(description = "Appointment status", example = "SCHEDULED")
    private String appointmentStatus;
    @Schema(description = "Diagnosis", example = "Healthy animal")
    private String diagnosis;
    @Schema(description = "Treatment", example = "Vaccination")
    private String treatment;
    @Schema(description = "Notes", example = "Follow up in 6 months")
    private String notes;

    @Schema(description = "Veterinarian ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID veterinarianId;
    @Schema(description = "Veterinarian name", example = "Dr. House")
    private String veterinarianName;
    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID clientId;
    @Schema(description = "Client name", example = "João Silva")
    private String clientName;
    @Schema(description = "Animal ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID animalId;
    @Schema(description = "Animal name", example = "Rex")
    private String animalName;
}
