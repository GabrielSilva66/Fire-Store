package com.firestore.domain.sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(Long id);
    List<Sale> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
