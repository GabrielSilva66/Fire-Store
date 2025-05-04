package com.firestore.domain.itemOrder;

import java.util.List;
import java.util.Optional;

public interface ItemOrderRepository {
    ItemOrder save(ItemOrder itemOrder);
    List<ItemOrder> findByOrderId(Long orderId);
    List<ItemOrder> findAll();
    void deleteById(Long id);
    Optional<ItemOrder> findById(Long id);


}
