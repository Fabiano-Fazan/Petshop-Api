package com.petshop.api.domain.sale;

import com.petshop.api.exception.BusinessException;
import com.petshop.api.model.entities.Sale;
import com.petshop.api.model.enums.SaleStatus;
import org.springframework.stereotype.Component;

@Component
public class SaleCancel {

    public void cancel(Sale sale) {
        if (sale.getStatus() == SaleStatus.CANCELED) {
            throw new BusinessException("This sale is already canceled");
        }

        boolean hasAnyPayment = sale.getFinancial() != null
                && sale.getFinancial().stream()
                .anyMatch(financial ->
                        financial.getFinancialPayments()
                        != null && !financial.getFinancialPayments().isEmpty()
                );

        if (hasAnyPayment) {
            throw new BusinessException(
                    "Cannot cancel a sale with registered payments."
            );
        }

        sale.setStatus(SaleStatus.CANCELED);
        sale.getFinancial().clear();
    }
}
