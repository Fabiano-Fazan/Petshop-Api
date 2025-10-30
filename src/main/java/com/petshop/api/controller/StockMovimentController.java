package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateStockMovementDTO;
import com.petshop.api.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockMovimentController {

    private final StockMovementService stockMovementService;

    @PostMapping("/input")
    public ResponseEntity<Void> giveInputStock(@RequestBody @Valid CreateStockMovementDTO createStockMovementDTO){
        stockMovementService.registerInput(createStockMovementDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/output")
    public ResponseEntity<Void> giveOutputStock(@RequestBody @Valid CreateStockMovementDTO createStockMovementDTO){
        stockMovementService.registerOutput(createStockMovementDTO);
        return ResponseEntity.ok().build();
    }
}
