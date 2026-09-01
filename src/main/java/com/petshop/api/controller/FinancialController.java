package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateFinancialDto;
import com.petshop.api.dto.request.CreateFinancialPaymentDto;
import com.petshop.api.dto.response.FinancialResponseDto;
import com.petshop.api.service.FinancialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/financial")
@Tag(name = "Financeiro", description = "Endpoints para gestão de financeiro")
public class FinancialController {
    private final FinancialService financialService;

    @GetMapping
    public ResponseEntity<Page<FinancialResponseDto>> getAllFinancial(@ParameterObject Pageable pageable){
        Page<FinancialResponseDto> financials = financialService.getAllFinancial(pageable);
        return ResponseEntity.ok(financials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialResponseDto> getFinancialById(@PathVariable UUID id){
        FinancialResponseDto financial = financialService.getFinancialById(id);
        return ResponseEntity.ok(financial);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<FinancialResponseDto>> getByClientName(@RequestParam String name, @ParameterObject Pageable pageable){
        Page<FinancialResponseDto> financials = financialService.getByClientNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(financials);
    }

    @PostMapping
    public ResponseEntity<List<FinancialResponseDto>> createFinancial(@Valid @RequestBody  CreateFinancialDto createFinancialDto){
        List<FinancialResponseDto> financials = financialService.createManualFinancial(createFinancialDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(financials);
    }

    @PatchMapping("/payment/{id}")
    public ResponseEntity<FinancialResponseDto> payFinancial(@PathVariable UUID id, @Valid @RequestBody CreateFinancialPaymentDto createFinancialPaymentDto){
        FinancialResponseDto paidFinancial = financialService.payFinancial(id, createFinancialPaymentDto);
        return ResponseEntity.ok(paidFinancial);
    }

    @PostMapping("/payments/{paymentId}/refund")
    public ResponseEntity<FinancialResponseDto> refundFinancial(@PathVariable UUID paymentId) {
        FinancialResponseDto financial = financialService.refundFinancial(paymentId);
        return ResponseEntity.ok(financial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancial(@PathVariable UUID id){
        financialService.deleteFinancial(id);
        return ResponseEntity.noContent().build();
    }
}
