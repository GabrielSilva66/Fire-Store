package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaOrderEntity;
import com.firestore.domain.order.Order;

public class OrderMapper {
    private OrderMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaOrderEntity toEntity(Order order) {
        JpaOrderEntity jpaOrderEntity = new JpaOrderEntity();
        jpaOrderEntity.setId(order.getId());
        jpaOrderEntity.setDate(order.getDate());
        jpaOrderEntity.setTotalValue(order.getTotalValue());
        jpaOrderEntity.setObservation(order.getObservation());
        jpaOrderEntity.setJpaEmployee(EmployeeMapper.toEntity(order.getEmployee()));
        jpaOrderEntity.setTotalQuantity(order.getTotalQuantity());
        jpaOrderEntity.setJpaSupplier(SupplierMapper.toEntity(order.getSupplier()));
        return jpaOrderEntity;
    }

    public static Order toDomain(JpaOrderEntity jpaOrderEntity) {
        return new Order(
                jpaOrderEntity.getDate(),
                EmployeeMapper.toDomain(jpaOrderEntity.getJpaEmployee()),
                jpaOrderEntity.getId(),
                jpaOrderEntity.getObservation(),
                SupplierMapper.toDomain(jpaOrderEntity.getJpaSupplier()),
                jpaOrderEntity.getTotalQuantity(),
                jpaOrderEntity.getTotalValue()
        );
    }
}

