package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaSupplierEntity;
import com.firestore.adapters.outbound.mapper.SupplierMapper;
import com.firestore.adapters.outbound.repositories.JpaSupplierRepository;
import com.firestore.domain.supplier.Supplier;
import com.firestore.domain.supplier.SupplierRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SupplierRepositoryImp implements SupplierRepository {
    private final JpaSupplierRepository jpaSupplierRepository;

    public SupplierRepositoryImp(JpaSupplierRepository jpaSupplierRepository) {
        this.jpaSupplierRepository = jpaSupplierRepository;
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        Optional<JpaSupplierEntity> entity = jpaSupplierRepository.findById(id);
        return entity.map(SupplierMapper::toDomain);
    }

    @Override
    public List<Supplier> findAll() {
        return jpaSupplierRepository.findAll().stream()
                .map(SupplierMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier save(Supplier supplier) {
        JpaSupplierEntity entity = SupplierMapper.toEntity(supplier);
        JpaSupplierEntity saved = jpaSupplierRepository.save(entity);
        return SupplierMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaSupplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findActiveSupplier() {
        return jpaSupplierRepository.findActiveSuppliers().stream()
                .map(SupplierMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateSupplierStatus(Long id, boolean status) {
        jpaSupplierRepository.updateSupplierStatus(id, status);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaSupplierRepository.existsById(id);
    }
}
