package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateProductCategoryDto;
import com.petshop.api.dto.response.ProductCategoryResponseDto;
import com.petshop.api.dto.update.UpdateProductCategoryDto;
import com.petshop.api.model.entities.ProductCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T22:36:55-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ProductCategoryMapperImpl implements ProductCategoryMapper {

    @Override
    public ProductCategory toEntity(CreateProductCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductCategory.ProductCategoryBuilder productCategory = ProductCategory.builder();

        productCategory.name( dto.getName() );
        productCategory.description( dto.getDescription() );

        return productCategory.build();
    }

    @Override
    public ProductCategoryResponseDto toResponseDto(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }

        ProductCategoryResponseDto productCategoryResponseDto = new ProductCategoryResponseDto();

        productCategoryResponseDto.setId( productCategory.getId() );
        productCategoryResponseDto.setName( productCategory.getName() );
        productCategoryResponseDto.setDescription( productCategory.getDescription() );

        return productCategoryResponseDto;
    }

    @Override
    public void updateProductCategoryFromDto(UpdateProductCategoryDto updateProductCategoryDto, ProductCategory productCategory) {
        if ( updateProductCategoryDto == null ) {
            return;
        }

        if ( updateProductCategoryDto.getName() != null ) {
            productCategory.setName( updateProductCategoryDto.getName() );
        }
        if ( updateProductCategoryDto.getDescription() != null ) {
            productCategory.setDescription( updateProductCategoryDto.getDescription() );
        }
    }
}
