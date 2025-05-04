package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaOrderEntity;
import com.firestore.adapters.outbound.mapper.OrderMapper;
import com.firestore.adapters.outbound.repositories.JpaOrderRepository;
import com.firestore.domain.order.Order;
import com.firestore.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImp implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryImp(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        JpaOrderEntity entity = OrderMapper.toEntity(order);
        JpaOrderEntity savedEntity = jpaOrderRepository.save(entity);
        return OrderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<JpaOrderEntity> entity = jpaOrderRepository.findById(id);
        return entity.map(OrderMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaOrderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll().stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }
}
