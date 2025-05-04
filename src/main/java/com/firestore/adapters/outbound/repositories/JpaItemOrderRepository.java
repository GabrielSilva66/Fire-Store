package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaItemOrderEntity;
import com.firestore.domain.itemOrder.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemOrderRepository extends JpaRepository<JpaItemOrderEntity, Long> {

    List<JpaItemOrderEntity> findByJpaOrderId(Long orderId);

}
