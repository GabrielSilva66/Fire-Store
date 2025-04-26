package com.firestore.application.service;



import com.firestore.adapters.outbound.repositories.JpaItemSaleRepository;
import com.firestore.adapters.outbound.repositories.JpaProductRepository;
import com.firestore.adapters.outbound.repositories.JpaSaleRepository;
import com.firestore.domain.itemSale.ItemSale;
import com.firestore.domain.product.Product;
import com.firestore.domain.sale.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {

    private final JpaSaleRepository saleRepository;
    private final JpaItemSaleRepository itemSaleRepository;
    private final JpaProductRepository productRepository;

    @Autowired
    public SaleService(JpaSaleRepository saleRepository, JpaItemSaleRepository itemSaleRepository,
                       JpaProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.itemSaleRepository = itemSaleRepository;
        this.productRepository = productRepository;
    }

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    public List<ItemSale> findItemsBySaleId(Long id) {
        return itemSaleRepository.findBySaleId(id);
    }

    public void saveSale(Sale sale, List<Long> productIds, List<Integer> quantities) {
        saleRepository.save(sale);

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Integer quantity = quantities.get(i);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            ItemSale itemSale = new ItemSale();
            itemSale.setSale(sale);
            itemSale.setProduct(product);
            itemSale.setQuantity(quantity);
            itemSale.setValue(product.getSalePrice().multiply(BigDecimal.valueOf(quantity)).doubleValue());

            itemSaleRepository.save(itemSale);
        }
    }
}
