package com.petshop.api.dto.response;


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
public class MedicalAppointmentResponseDTO {
    private UUID id;
    private LocalDateTime appointmentDate;
    private String status;
    private String reason;
    private String diagnosis;
    private String treatment;
    private String notes;

    private UUID veterinarianId;
    private String veterinarianName;
    private UUID clientId;
    private String clientName;
    private UUID animalId;
    private String animalName;
}
