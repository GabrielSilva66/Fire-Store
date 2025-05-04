package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaSupplierEntity;
import com.firestore.domain.supplier.Supplier;

public class SupplierMapper {

    private SupplierMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaSupplierEntity toEntity(Supplier supplier) {
        JpaSupplierEntity jpaSupplierEntity = new JpaSupplierEntity();
        jpaSupplierEntity.setId(supplier.getId());
        jpaSupplierEntity.setName(supplier.getName());
        jpaSupplierEntity.setEmail(supplier.getEmail());
        jpaSupplierEntity.setTelephone(supplier.getTelephone());
        jpaSupplierEntity.setActive(supplier.isActive());
        jpaSupplierEntity.setJpaAddress(AddressMapper.toAddressEntity(supplier.getAddress()));
        jpaSupplierEntity.setCnpj(supplier.getCnpj());
        return jpaSupplierEntity;
    }

    public static Supplier toDomain(JpaSupplierEntity jpaSupplierEntity) {
        return new Supplier(
                AddressMapper.toDomain(jpaSupplierEntity.getJpaAddress()),
                jpaSupplierEntity.getCnpj(),
                jpaSupplierEntity.getEmail(),
                jpaSupplierEntity.getId(),
                jpaSupplierEntity.isActive(),
                jpaSupplierEntity.getName(),
                jpaSupplierEntity.getTelephone()
        );
    }
}
