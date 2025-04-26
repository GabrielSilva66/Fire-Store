package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSaleRepository extends JpaRepository<JpaSaleEntity, Long> {
}
