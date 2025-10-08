package com.petshop.api.model.mapper;

import com.petshop.api.dto.CreateSaleDTO;
import com.petshop.api.dto.ProductSaleDTO;
import com.petshop.api.dto.SaleDTO;
import com.petshop.api.model.entities.ProductSale;
import com.petshop.api.model.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "client.name", target = "clientName")
    SaleDTO toDto(Sale sale);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ProductSaleDTO toProductSaleDto(ProductSale productSale);

}
