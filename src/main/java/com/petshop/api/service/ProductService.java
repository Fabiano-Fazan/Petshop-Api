package com.petshop.api.service;

import com.petshop.api.dto.request.CreateProductDTO;
import com.petshop.api.dto.request.UpdateProductDTO;
import com.petshop.api.dto.response.ProductResponseDTO;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.ProductCategory;
import com.petshop.api.model.mapper.ProductMapper;
import com.petshop.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponseDto);
    }

    public Page<ProductResponseDTO> getProductByCategory(ProductCategory productCategory, Pageable pageable) {
        return productRepository.findByCategory(productCategory, pageable)
                .map(productMapper::toResponseDto);
    }

    public Page<ProductResponseDTO> getProductByName(String name, Pageable pageable){
        return productRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(productMapper::toResponseDto);
    }

    public ProductResponseDTO getProductById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Transactional
    public ProductResponseDTO createProduct(CreateProductDTO createProductDTO) {
        Product product = productMapper.toEntity(createProductDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID id, UpdateProductDTO updateProductDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productMapper.updateProductFromDTO(updateProductDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponseDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
            }
        productRepository.deleteById(id);
    }
}
