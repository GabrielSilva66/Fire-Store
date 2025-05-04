package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaItemOrderEntity;
import com.firestore.domain.itemOrder.ItemOrder;

public class ItemOrderMapper {

    private ItemOrderMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaItemOrderEntity toEntity(ItemOrder itemOrder) {
        JpaItemOrderEntity jpaItemOrderEntity = new JpaItemOrderEntity();

        jpaItemOrderEntity.setId(itemOrder.getId());
        jpaItemOrderEntity.setValue(itemOrder.getValue());
        jpaItemOrderEntity.setQuantity(itemOrder.getQuantity());
        jpaItemOrderEntity.setJpaOrder(OrderMapper.toEntity(itemOrder.getOrder()));
        jpaItemOrderEntity.setJpaProduct(ProductMapper.toEntity(itemOrder.getProduct()));

        return jpaItemOrderEntity;

    }

    public static ItemOrder toDomain(JpaItemOrderEntity jpaItemOrderEntity) {
        return new ItemOrder(
                jpaItemOrderEntity.getId(),
                OrderMapper.toDomain(jpaItemOrderEntity.getJpaOrder()),
                ProductMapper.toDomain(jpaItemOrderEntity.getJpaProduct()),
                jpaItemOrderEntity.getQuantity(),
                jpaItemOrderEntity.getValue()
        );
    }
}
