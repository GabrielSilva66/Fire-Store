package com.project.system.repositories;

import com.project.system.models.ItemSale;
import com.project.system.models.Order;
import com.project.system.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
}
