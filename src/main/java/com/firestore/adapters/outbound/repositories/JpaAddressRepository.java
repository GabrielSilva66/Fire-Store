package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.domain.Address.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaAddressRepository extends JpaRepository<JpaAddressEntity, Long> {

    @Query(value = "SELECT * FROM tb_address WHERE is_active = true", nativeQuery = true)
    List<JpaAddressEntity> findActiveAddress();


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_address SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateAddressStatus(@Param("id") Long id, @Param("status") boolean status);

}
