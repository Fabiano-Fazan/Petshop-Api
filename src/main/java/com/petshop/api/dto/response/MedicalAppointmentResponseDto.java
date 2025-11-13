package com.petshop.api.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentEndTime;

    private String appointmentStatus;
    private String diagnosis;
    private String treatment;

    private UUID veterinarianId;
    private String veterinarianName;
    private UUID clientId;
    private String clientName;
    private UUID animalId;
    private String animalName;
}
