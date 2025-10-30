package com.petshop.api.repository;

import com.petshop.api.model.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {


}
