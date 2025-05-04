package com.firestore.application.service;

import com.firestore.application.usecases.SupplierUseCases;
import com.firestore.domain.supplier.Supplier;
import com.firestore.domain.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService  implements SupplierUseCases {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAllActiveSuppliers() {
        return supplierRepository.findActiveSupplier();
    }

    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public boolean deactivateSupplier(Long id) {
        if(!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }

        supplierRepository.updateSupplierStatus(id, false);
        return true;
    }
}
