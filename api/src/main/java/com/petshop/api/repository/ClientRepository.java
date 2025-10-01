package com.petshop.api.repository;

import com.petshop.api.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {




}
