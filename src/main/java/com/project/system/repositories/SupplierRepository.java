package com.project.system.repositories;

import com.project.system.models.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query(value = "SELECT * FROM tb_supplier WHERE is_active = true", nativeQuery = true)
    List<Supplier> findActiveSupplier();


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_supplier SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateSupplierStatus(@Param("id") Long id, @Param("status") boolean status);
}
