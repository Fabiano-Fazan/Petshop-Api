package com.petshop.api.model.mapper;

import com.petshop.api.dto.response.ProductSaleResponseDTO;
import com.petshop.api.dto.response.SaleResponseDTO;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.ProductSale;
import com.petshop.api.model.entities.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T12:59:01-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleResponseDTO toResponseDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleResponseDTO saleResponseDTO = new SaleResponseDTO();

        saleResponseDTO.setClientName( saleClientName( sale ) );
        saleResponseDTO.setId( sale.getId() );
        saleResponseDTO.setSaleDate( sale.getSaleDate() );
        saleResponseDTO.setTotalValue( sale.getTotalValue() );
        saleResponseDTO.setProductSales( productSaleListToProductSaleResponseDTOList( sale.getProductSales() ) );

        return saleResponseDTO;
    }

    @Override
    public ProductSaleResponseDTO toProductSaleDto(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }

        ProductSaleResponseDTO productSaleResponseDTO = new ProductSaleResponseDTO();

        productSaleResponseDTO.setProductId( productSaleProductId( productSale ) );
        productSaleResponseDTO.setProductName( productSaleProductName( productSale ) );
        productSaleResponseDTO.setQuantity( productSale.getQuantity() );
        productSaleResponseDTO.setUnitPrice( productSale.getUnitPrice() );

        return productSaleResponseDTO;
    }

    private String saleClientName(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Client client = sale.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<ProductSaleResponseDTO> productSaleListToProductSaleResponseDTOList(List<ProductSale> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductSaleResponseDTO> list1 = new ArrayList<ProductSaleResponseDTO>( list.size() );
        for ( ProductSale productSale : list ) {
            list1.add( toProductSaleDto( productSale ) );
        }

        return list1;
    }

    private UUID productSaleProductId(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }
        Product product = productSale.getProduct();
        if ( product == null ) {
            return null;
        }
        UUID id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productSaleProductName(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }
        Product product = productSale.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
