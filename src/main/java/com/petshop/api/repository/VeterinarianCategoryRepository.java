package com.petshop.api.repository;

import com.petshop.api.model.entities.VeterinarianCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VeterinarianCategoryRepository extends JpaRepository<VeterinarianCategory, UUID> {
    Optional<VeterinarianCategory> findByNameContainingIgnoreCase(String name);
}
