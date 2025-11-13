package com.petshop.api.repository;

import com.petshop.api.model.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
   Optional<ProductCategory> findByNameContainingIgnoreCase(String name);

}
