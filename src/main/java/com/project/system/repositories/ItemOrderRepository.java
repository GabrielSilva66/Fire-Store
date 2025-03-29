package com.project.system.repositories;

import com.project.system.models.ItemOrder;
import com.project.system.models.ItemSale;
import com.project.system.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

    List<ItemOrder> findByOrderId(Long saleId);
}
