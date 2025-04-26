package com.firestore.domain.itemSale;

import java.util.List;

public interface ItemSaleRepository {
    void save(ItemSale itemSale);
    void deleteById(Long id);
    ItemSale findById(Long id);
    List<ItemSale> findAll();
    List<ItemSale> findBySaleId(Long saleId);
}
