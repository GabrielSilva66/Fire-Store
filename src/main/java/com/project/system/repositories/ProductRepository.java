package com.project.system.repositories;

import com.project.system.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE tb_product SET is_active = :status WHERE id = :id", nativeQuery = true)
//    void updateProductStatus(@Param("id") Long id, @Param("status") boolean status);
}
