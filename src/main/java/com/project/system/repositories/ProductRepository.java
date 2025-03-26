package com.project.system.repositories;

import com.project.system.models.Customer;
import com.project.system.models.Intake;
import com.project.system.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE tb_product SET is_active = :status WHERE id = :id", nativeQuery = true)
//    void updateProductStatus(@Param("id") Long id, @Param("status") boolean status);
}
