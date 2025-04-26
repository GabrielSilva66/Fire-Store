package com.firestore.domain.sale;

import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    Sale findById(Long id);
    List<Sale> findAll();
    void deleteById(Long id);
}
