package com.firestore.domain.supplier;

import java.util.List;

public interface SupplierRepository {
    Supplier findById(Long id);
    List<Supplier> findAll();
    Supplier save(Supplier supplier);
    void deleteById(Long id);
}
