package com.firestore.application.usecases;

import com.firestore.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierUseCases {

    public List<Supplier> findAllActiveSuppliers();

    public Supplier findById(Long id);

    public void saveSupplier(Supplier supplier);

    public boolean deactivateSupplier(Long id);
}
