package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<JpaOrderEntity, Long> {

}
