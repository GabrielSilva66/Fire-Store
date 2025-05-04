package com.firestore.application.usecases;

import com.firestore.domain.itemSale.ItemSale;
import com.firestore.domain.product.Product;
import com.firestore.domain.sale.Sale;

import java.math.BigDecimal;
import java.util.List;

public interface SaleUseCases {

    public List<Sale> findAllSales();

    public List<ItemSale> findItemsBySaleId(Long id);

    public void saveSale(Sale sale, List<Long> productIds, List<Integer> quantities);

}
