package com.petshop.api.repository;

import com.petshop.api.model.entities.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, UUID> {
}
