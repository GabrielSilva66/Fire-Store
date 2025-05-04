package com.firestore.application.service;

import com.firestore.application.usecases.SaleUseCases;
import com.firestore.domain.itemSale.ItemSale;
import com.firestore.domain.itemSale.ItemSaleRepository;
import com.firestore.domain.product.Product;
import com.firestore.domain.product.ProductRepository;
import com.firestore.domain.sale.Sale;
import com.firestore.domain.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService implements SaleUseCases {

    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, ItemSaleRepository itemSaleRepository,
                       ProductRepository productRepository) {
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
            if(!saleRepository.existsById(productId)) {
                throw new RuntimeException("Product not found");
            }
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            Integer quantity = quantities.get(i);

            ItemSale itemSale = new ItemSale();
            itemSale.setSale(sale);
            itemSale.setProduct(product);
            itemSale.setQuantity(quantity);
            itemSale.setValue(product.getSalePrice().multiply(BigDecimal.valueOf(quantity)).doubleValue());

            itemSaleRepository.save(itemSale);
        }
    }
}
