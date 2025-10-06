package com.petshop.api.model.entities;

import com.petshop.api.dto.CreateProductDTO;
import com.petshop.api.dto.ProductDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-05T23:03:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(CreateProductDTO createProductDTO) {
        if ( createProductDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( createProductDTO.getName() );
        product.setDescription( createProductDTO.getDescription() );
        product.setPrice( createProductDTO.getPrice() );
        product.setCategory( createProductDTO.getCategory() );

        return product;
    }

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setCategory( product.getCategory() );

        return productDTO;
    }

    @Override
    public void updateProductFromDTO(CreateProductDTO createProductDTO, Product product) {
        if ( createProductDTO == null ) {
            return;
        }

        product.setName( createProductDTO.getName() );
        product.setDescription( createProductDTO.getDescription() );
        product.setPrice( createProductDTO.getPrice() );
        product.setCategory( createProductDTO.getCategory() );
    }
}
