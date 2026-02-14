package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateProductDto;
import com.petshop.api.dto.response.ProductResponseDto;
import com.petshop.api.dto.update.UpdateProductDto;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.ProductCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T22:36:55-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(CreateProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        if ( dto.getQuantityInStock() != null ) {
            product.quantityInStock( dto.getQuantityInStock() );
        }
        else {
            product.quantityInStock( 0 );
        }
        product.name( dto.getName() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );

        return product.build();
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setCategory( productCategoryName( product ) );
        productResponseDto.setId( product.getId() );
        productResponseDto.setName( product.getName() );
        productResponseDto.setDescription( product.getDescription() );
        productResponseDto.setPrice( product.getPrice() );
        productResponseDto.setQuantityInStock( product.getQuantityInStock() );

        return productResponseDto;
    }

    @Override
    public void updateProductFromDto(UpdateProductDto updateProductDto, Product product) {
        if ( updateProductDto == null ) {
            return;
        }

        if ( updateProductDto.getName() != null ) {
            product.setName( updateProductDto.getName() );
        }
        if ( updateProductDto.getDescription() != null ) {
            product.setDescription( updateProductDto.getDescription() );
        }
        if ( updateProductDto.getPrice() != null ) {
            product.setPrice( updateProductDto.getPrice() );
        }
    }

    private String productCategoryName(Product product) {
        ProductCategory category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
