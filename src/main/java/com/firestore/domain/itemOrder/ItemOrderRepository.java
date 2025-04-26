package com.firestore.domain.itemOrder;

import java.util.List;

public interface ItemOrderRepository {
    ItemOrder save(ItemOrder itemOrder);
    List<ItemOrder> findByOrderId(Long orderId);
    void deleteById(Long id);


}
