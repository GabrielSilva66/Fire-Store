package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<JpaProductEntity, Long> {

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE tb_product SET is_active = :status WHERE id = :id", nativeQuery = true)
//    void updateProductStatus(@Param("id") Long id, @Param("status") boolean status);
}
