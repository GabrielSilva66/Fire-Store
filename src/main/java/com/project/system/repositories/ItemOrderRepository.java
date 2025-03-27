package com.project.system.repositories;

import com.project.system.models.ItemOrder;
import com.project.system.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {
}
