package com.project.system.repositories;

import com.project.system.models.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
    List<ItemSale> findBySaleId(Long saleId);
}
