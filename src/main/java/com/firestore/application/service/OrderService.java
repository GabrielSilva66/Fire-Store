package com.firestore.application.service;

import com.firestore.application.usecases.OrderUseCases;
import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.domain.itemOrder.ItemOrderRepository;
import com.firestore.domain.order.Order;
import com.firestore.domain.order.OrderRepository;
import com.firestore.domain.product.Product;
import com.firestore.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService implements OrderUseCases {

    private final OrderRepository orderRepository;
    private final ItemOrderRepository itemOrderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ItemOrderRepository itemOrderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.itemOrderRepository = itemOrderRepository;
        this.productRepository = productRepository;
    }

    public Order saveOrderWithItems(Order order, List<Long> productIds, List<Integer> quantities) {
//        orderRepository.save(order);

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Integer quantity = quantities.get(i);

            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setOrder(order);
            itemOrder.setProduct(product);
            itemOrder.setQuantity(quantity);
            itemOrder.setValue(product.getSalePrice()
                    .multiply(BigDecimal.valueOf(quantity)).doubleValue());

            itemOrderRepository.save(itemOrder);
        }

        return order;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<ItemOrder> getItemsByOrderId(Long orderId) {
        return itemOrderRepository.findByOrderId(orderId);
    }
}
