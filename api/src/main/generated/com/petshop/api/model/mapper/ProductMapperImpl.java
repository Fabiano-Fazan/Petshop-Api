package com.petshop.api.model.mapper;

import com.petshop.api.dto.CreateProductDTO;
import com.petshop.api.dto.ProductDTO;
import com.petshop.api.model.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-07T21:08:51-0300",
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

        product.name( createProductDTO.getName() );
        product.description( createProductDTO.getDescription() );
        product.price( createProductDTO.getPrice() );
        product.category( createProductDTO.getCategory() );

        return product.build();
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
