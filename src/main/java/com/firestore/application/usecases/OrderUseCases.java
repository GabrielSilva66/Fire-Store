package com.firestore.application.usecases;

import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.domain.order.Order;

import java.util.List;

public interface OrderUseCases {
    public Order saveOrderWithItems(Order order, List<Long> productIds, List<Integer> quantities);

    public List<Order> findAllOrders();

    public List<ItemOrder> getItemsByOrderId(Long orderId);


}
