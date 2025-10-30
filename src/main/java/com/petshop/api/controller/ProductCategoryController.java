package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateProductCategoryDTO;
import com.petshop.api.dto.request.UpdateProductCategoryDTO;
import com.petshop.api.dto.response.ProductCategoryResponseDTO;
import com.petshop.api.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private  final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<Page<ProductCategoryResponseDTO>> getAllProductCategories(Pageable pageable) {
        Page<ProductCategoryResponseDTO> allProductCategories = productCategoryService.getAllProductCategories(pageable);
        return ResponseEntity.ok(allProductCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponseDTO> getProductCategoryById(@PathVariable UUID id){
        ProductCategoryResponseDTO productCategoryById = productCategoryService.getProductCategoryById(id);
        return ResponseEntity.ok(productCategoryById);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Page<ProductCategoryResponseDTO>> getByName(@PathVariable("name") String name, Pageable pageable){
        Page<ProductCategoryResponseDTO> productCategoryByName = productCategoryService.getByName(name, pageable);
        return ResponseEntity.ok(productCategoryByName);
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResponseDTO> createProductCategory(@Valid @RequestBody CreateProductCategoryDTO createProductCategoryDTO){
        ProductCategoryResponseDTO createdProductCategory = productCategoryService.createProductCategory(createProductCategoryDTO);
        return new ResponseEntity<>(createdProductCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductCategoryResponseDTO> updateProductCategory(@PathVariable UUID id, @Valid @RequestBody UpdateProductCategoryDTO updateProductCategoryDTO){
        ProductCategoryResponseDTO updatedProductCategory = productCategoryService.updateProductCategory(id, updateProductCategoryDTO);
        return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable UUID id){
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
