package com.firestore.adapters.outbound.repositories;

import com.firestore.adapters.outbound.entities.JpaItemSaleEntity;
import com.firestore.domain.itemSale.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemSaleRepository extends JpaRepository<JpaItemSaleEntity, Long> {
    List<ItemSale> findBySaleId(Long saleId);
}
