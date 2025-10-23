package com.petshop.api.service;

import com.petshop.api.dto.request.CreateProductSaleDTO;
import com.petshop.api.dto.request.CreateSaleDTO;
import com.petshop.api.dto.response.SaleDTO;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.ProductSale;
import com.petshop.api.model.entities.Sale;

import com.petshop.api.model.enums.SaleStatus;
import com.petshop.api.model.mapper.SaleMapper;
import com.petshop.api.repository.ClientRepository;
import com.petshop.api.repository.ProductRepository;
import com.petshop.api.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final StockMovimentService stockMovimentService;

    @Transactional
    public SaleDTO createSale(CreateSaleDTO createSaleDTO) {
        Client client = clientRepository.findById(createSaleDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + createSaleDTO.getClientId()));

        Sale newSale = new Sale();

        newSale.setClient(client);
        newSale.setSaleDate(LocalDateTime.now());
        newSale.setStatus(SaleStatus.COMPLETED);

        BigDecimal totalValue = BigDecimal.ZERO;

        for (CreateProductSaleDTO productSaleDTO : createSaleDTO.getProductSales()) {
            Product product = productRepository.findById(productSaleDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            ProductSale productSale = new ProductSale();
            productSale.setProduct(product);
            productSale.setQuantity(productSaleDTO.getQuantity());
            productSale.setUnitPrice(product.getPrice());
            productSale.setSale(newSale);
            newSale.getProductSales().add(productSale);

            totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(productSaleDTO.getQuantity())));
        }
        newSale.setTotalValue(totalValue);

        Sale savedSale = saleRepository.save(newSale);

        for (ProductSale productSold : savedSale.getProductSales()) {
            String description = "SALE_ORDER_" + savedSale.getId();
            stockMovimentService.registerOutput(
                    productSold.getProduct(),
                    productSold.getQuantity(),
                    description,
                    savedSale
            );
        }
        return saleMapper.toDto(savedSale);
    }

    public SaleDTO findById(UUID id) {
        return saleRepository.findById(id)
                .map(saleMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + id));
    }

    public Page<SaleDTO> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable)
                .map(saleMapper::toDto);
    }

    @Transactional
    public SaleDTO cancelSale(UUID id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + id));

        if (sale.getStatus() == SaleStatus.CANCELED) {
            throw new IllegalStateException("This sale is already canceled");
        }

        sale.setStatus(SaleStatus.CANCELED);
        Sale canceledSale = saleRepository.save(sale);

        for (ProductSale productSold : canceledSale.getProductSales()) {
            String description = "CANCELATION_OF_SALE_ORDER_" + canceledSale.getId();

            stockMovimentService.registerInput(
                    productSold.getProduct(),
                    productSold.getQuantity(),
                    description,
                    null
            );
        }
        return saleMapper.toDto(canceledSale);
    }
}


