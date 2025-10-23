package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateStockMoviment;
import com.petshop.api.service.StockMovimentService;
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

    private final StockMovimentService stockMovimentService;

    @PostMapping("/input")
    public ResponseEntity<Void> giveInputStock(@RequestBody @Valid CreateStockMoviment createStockMoviment){
        stockMovimentService.registerInput(createStockMoviment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/output")
    public ResponseEntity<Void> giveOutputStock(@RequestBody @Valid CreateStockMoviment createStockMoviment){
        stockMovimentService.registerOutput(createStockMoviment);
        return ResponseEntity.ok().build();
    }
}
