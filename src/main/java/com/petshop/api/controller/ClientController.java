package com.petshop.api.controller;

import com.petshop.api.dto.request.UpdateClientDTO;
import com.petshop.api.dto.response.ClientResponseDTO;
import com.petshop.api.dto.request.CreateClientDTO;
import com.petshop.api.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable) {
        Page<ClientResponseDTO> allClients = clientService.getAllClients(pageable);
        return ResponseEntity.ok(allClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID id) {
        ClientResponseDTO clientById = clientService.getClientById(id);
        return ResponseEntity.ok(clientById);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ClientResponseDTO>> getClientByName(@PathVariable String name, Pageable pageable) {
        Page<ClientResponseDTO> clientByName = clientService.getClientByNameContainingIgnoreCase(name,pageable);
        return ResponseEntity.ok(clientByName);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        ClientResponseDTO createdClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id, @Valid @RequestBody UpdateClientDTO updateClientDTO) {
        ClientResponseDTO updatedClient = clientService.updateClient(id, updateClientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
