package com.petshop.api.repository;

import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.MedicalAppointment;
import com.petshop.api.model.entities.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, UUID> {

    @Query("""
            SELECT EXISTS(
            SELECT 1 FROM MedicalAppointment a
            WHERE a.veterinarian.id = :veterinarianId
            AND a.appointmentStatus = com.petshop.api.model.enums.AppointmentStatus.SCHEDULED
            AND :start < a.appointmentEndTime
            AND :end > a.appointmentStartTime
            )
            """)
    boolean existsConflictingAppointment(
            @Param("veterinarianId") UUID veterinarianId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
           SELECT EXISTS(
           SELECT a FROM MedicalAppointment a
           WHERE a.client.id = :clientId
           AND a.appointmentStatus = com.petshop.api.model.enums.AppointmentStatus.SCHEDULED
           AND :start < a.appointmentEndTime
           AND :end > a.appointmentStartTime)
           """)
    boolean existsConflictingAppointmentByClient(
            @Param("clientId") UUID clientId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    Page<MedicalAppointment> findByClientNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("""
        SELECT EXISTS(
        SELECT 1 FROM MedicalAppointment a
        WHERE a.veterinarian.id = :veterinarianId
        AND a.appointmentStatus = com.petshop.api.model.enums.AppointmentStatus.SCHEDULED
        AND a.id != :currentAppointmentId
        AND :start < a.appointmentEndTime
        AND :end > a.appointmentStartTime
        )
        """)
    boolean existsConflictingAppointmentForUpdate(
            @Param("veterinarianId") UUID veterinarianId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("currentAppointmentId") UUID currentAppointmentId
    );

    @Query("""
        SELECT EXISTS(
        SELECT 1 FROM MedicalAppointment a
        WHERE a.client.id = :clientId
        AND a.appointmentStatus = com.petshop.api.model.enums.AppointmentStatus.SCHEDULED
        AND a.id != :currentAppointmentId
        AND :start < a.appointmentEndTime
        AND :end > a.appointmentStartTime
        )
        """)
    boolean existsConflictingAppointmentByClientForUpdate(
            @Param("clientId") UUID clientId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("currentAppointmentId") UUID currentAppointmentId
    );



    Page<MedicalAppointment> findByVeterinarianNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByVeterinarian(Veterinarian veterinarian);
    boolean existsByAnimal(Animal animal);
    boolean existsByClient(Client client);
}
