package com.firestore.domain.order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
    void deleteById(Long id);
    List<Order> findAll();
    List<Order> findByCustomerId(Long customerId);

}
