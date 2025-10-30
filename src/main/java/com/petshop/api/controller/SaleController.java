package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateSaleDTO;
import com.petshop.api.dto.response.SaleResponseDTO;
import com.petshop.api.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale")
public class SaleController {
    private  final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody @Valid CreateSaleDTO createSaleDTO){
        SaleResponseDTO createdSale = saleService.createSale(createSaleDTO);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SaleResponseDTO> findSaleById(@PathVariable UUID id){
        SaleResponseDTO sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping
    public ResponseEntity<Page<SaleResponseDTO>> getAllSales(Pageable pageable){
        Page<SaleResponseDTO> sales = saleService.getAllSales(pageable);
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<SaleResponseDTO> cancelSale(@PathVariable UUID id){
        SaleResponseDTO canceledSale = saleService.cancelSale(id);
        return ResponseEntity.ok(canceledSale);
    }
}
