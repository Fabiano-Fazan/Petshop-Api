package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateSaleDTO;
import com.petshop.api.dto.response.SaleDTO;
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
    public ResponseEntity<SaleDTO> createSale(@RequestBody @Valid CreateSaleDTO createSaleDTO){
        SaleDTO newSale = saleService.createSale(createSaleDTO);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SaleDTO> findSaleById(@PathVariable UUID id){
        SaleDTO sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> getAllSales(Pageable pageable){
        Page<SaleDTO> sales = saleService.getAllSales(pageable);
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<SaleDTO> cancelSale(@PathVariable UUID id){
        SaleDTO canceledSale = saleService.cancelSale(id);
        return ResponseEntity.ok(canceledSale);
    }
}
