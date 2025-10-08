package com.petshop.api.repository;

import com.petshop.api.model.entities.StockMoviment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockMovimentRepository extends JpaRepository<StockMoviment, UUID> {
}
