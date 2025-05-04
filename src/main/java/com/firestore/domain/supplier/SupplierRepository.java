package com.firestore.domain.supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    Optional<Supplier> findById(Long id);
    List<Supplier> findAll();
    Supplier save(Supplier supplier);
    void deleteById(Long id);
    List<Supplier> findActiveSupplier();
    void updateSupplierStatus(Long id, boolean status);
    boolean existsById(Long id);
}
