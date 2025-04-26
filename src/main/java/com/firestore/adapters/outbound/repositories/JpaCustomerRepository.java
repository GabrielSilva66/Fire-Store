package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaCustomerEntity;
import com.firestore.domain.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, Long> {
    @Query(value = "SELECT * FROM tb_customer WHERE is_active = true", nativeQuery = true)
    List<Customer> findActiveCustomer();


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_costumer SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateCustomerStatus(@Param("id") Long id, @Param("status") boolean status);
}
