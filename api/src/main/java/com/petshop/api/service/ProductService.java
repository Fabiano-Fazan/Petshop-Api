package com.petshop.api.service;

import com.petshop.api.dto.CreateProductDTO;
import com.petshop.api.dto.ProductDTO;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.enums.ProductCategory;
import com.petshop.api.model.mapper.ProductMapper;
import com.petshop.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    public Page<ProductDTO> findProductByCategory(ProductCategory productCategory,Pageable pageable) {
        return productRepository.findByCategory(productCategory, pageable)
                .map(productMapper::toDto);
    }

    public Page<ProductDTO>  findProductByName(String name, Pageable pageable){
        return productRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(productMapper::toDto);
    }

    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
        Product product = productMapper.toEntity(createProductDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public ProductDTO updateProduct(UUID id, CreateProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productMapper.updateProductFromDTO(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    public ProductDTO getProductById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
