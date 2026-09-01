package com.petshop.api.repository;

import com.petshop.api.model.entities.Client;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByCpf(String cpf);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Client> findWithLockById(UUID id);
}
