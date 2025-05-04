package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaSupplierEntity;
import com.firestore.domain.supplier.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSupplierRepository extends JpaRepository<JpaSupplierEntity, Long> {
    @Query(value = "SELECT * FROM tb_supplier WHERE is_active = true", nativeQuery = true)
    List<JpaSupplierEntity> findActiveSuppliers();


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_supplier SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateSupplierStatus(@Param("id") Long id, @Param("status") boolean status);
}
