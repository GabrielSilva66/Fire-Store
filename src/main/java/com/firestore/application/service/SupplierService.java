package com.firestore.application.service;

import com.firestore.domain.supplier.Supplier;
import com.firestore.adapters.outbound.repositories.JpaSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final JpaSupplierRepository supplierRepository;

    @Autowired
    public SupplierService(JpaSupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAllActiveSuppliers() {
        return supplierRepository.findActiveSupplier();
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public boolean deactivateSupplier(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            supplierRepository.updateSupplierStatus(id, false);
            return true;
        }
        return false;
    }
}
