package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateMonetaryType;
import com.petshop.api.dto.update.UpdateMonetaryTypeDto;
import com.petshop.api.dto.response.MonetaryTypeResponseDto;
import com.petshop.api.service.MonetaryTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/monetary-types")
@RequiredArgsConstructor
@Tag(name = "Tipo Monetário", description = "Endpoints para gestão de tipos monetários")
public class MonetaryTypeController {

    private final MonetaryTypeService monetaryTypeService;

    @GetMapping
    public ResponseEntity<Page<MonetaryTypeResponseDto>> getAllMonetaryTypes(@ParameterObject Pageable pageable) {
        Page<MonetaryTypeResponseDto> allMonetaryTypes = monetaryTypeService.getAllMonetaryTypes(pageable);
        return ResponseEntity.ok(allMonetaryTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonetaryTypeResponseDto> getMonetaryTypeById(@PathVariable UUID id){
        MonetaryTypeResponseDto monetaryTypeById = monetaryTypeService.getMonetaryTypeById(id);
        return ResponseEntity.ok(monetaryTypeById);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<MonetaryTypeResponseDto>> getByName(@RequestParam String name, @ParameterObject Pageable pageable){
        Page<MonetaryTypeResponseDto> monetaryTypeByName = monetaryTypeService.getMonetaryTypeByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(monetaryTypeByName);
    }

    @PostMapping
    public ResponseEntity<MonetaryTypeResponseDto> createMonetaryType(@Valid @RequestBody CreateMonetaryType createMonetaryTypeDTO){
        MonetaryTypeResponseDto createdMonetaryType = monetaryTypeService.createMonetaryType(createMonetaryTypeDTO);
        return new ResponseEntity<>(createdMonetaryType, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MonetaryTypeResponseDto> updateMonetaryType(@PathVariable UUID id,  @Valid @RequestBody UpdateMonetaryTypeDto updateMonetaryTypeDto){
        MonetaryTypeResponseDto updatedMonetaryType = monetaryTypeService.updateMonetaryType(id, updateMonetaryTypeDto);
        return ResponseEntity.ok(updatedMonetaryType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonetaryType(@PathVariable UUID id){
        monetaryTypeService.deleteMonetaryType(id);
        return ResponseEntity.noContent().build();
    }
}
