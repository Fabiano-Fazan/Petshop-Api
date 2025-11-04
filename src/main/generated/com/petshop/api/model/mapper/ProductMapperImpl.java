package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateProductDTO;
import com.petshop.api.dto.request.UpdateProductDTO;
import com.petshop.api.dto.response.ProductResponseDTO;
import com.petshop.api.model.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T12:59:01-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(CreateProductDTO createProductDTO) {
        if ( createProductDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        if ( createProductDTO.getQuantityInStock() != null ) {
            product.quantityInStock( createProductDTO.getQuantityInStock() );
        }
        else {
            product.quantityInStock( 0 );
        }
        product.name( createProductDTO.getName() );
        product.description( createProductDTO.getDescription() );
        product.price( createProductDTO.getPrice() );
        product.category( createProductDTO.getCategory() );

        return product.build();
    }

    @Override
    public ProductResponseDTO toResponseDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setCategory( product.getCategory() );

        return productResponseDTO;
    }

    @Override
    public void updateProductFromDTO(UpdateProductDTO updateProductDTO, Product product) {
        if ( updateProductDTO == null ) {
            return;
        }

        if ( updateProductDTO.getName() != null ) {
            product.setName( updateProductDTO.getName() );
        }
        if ( updateProductDTO.getDescription() != null ) {
            product.setDescription( updateProductDTO.getDescription() );
        }
        if ( updateProductDTO.getPrice() != null ) {
            product.setPrice( updateProductDTO.getPrice() );
        }
        if ( updateProductDTO.getCategory() != null ) {
            product.setCategory( updateProductDTO.getCategory() );
        }
    }
}
