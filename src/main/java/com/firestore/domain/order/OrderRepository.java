package com.firestore.domain.order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    void deleteById(Long id);
    List<Order> findAll();

}
