package com.firestore.domain.itemSale;

import java.util.List;
import java.util.Optional;

public interface ItemSaleRepository {
    void save(ItemSale itemSale);
    void deleteById(Long id);
    Optional<ItemSale> findById(Long id);
    List<ItemSale> findAll();
    List<ItemSale> findBySaleId(Long saleId);

}
