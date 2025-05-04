package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaItemSaleEntity;
import com.firestore.domain.itemSale.ItemSale;

public class ItemSaleMapper {
    private ItemSaleMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaItemSaleEntity toEntity(ItemSale itemSale) {
        JpaItemSaleEntity jpaItemSaleEntity = new JpaItemSaleEntity();

        jpaItemSaleEntity.setId(itemSale.getId());
        jpaItemSaleEntity.setValue(itemSale.getValue());
        jpaItemSaleEntity.setQuantity(itemSale.getQuantity());
        jpaItemSaleEntity.setJpaProduct(ProductMapper.toEntity(itemSale.getProduct()));
        jpaItemSaleEntity.setJpaSale(SaleMapper.toEntity(itemSale.getSale()));

        return jpaItemSaleEntity;
    }

    public static ItemSale toDomain(JpaItemSaleEntity jpaItemSaleEntity) {
        return new ItemSale(
                jpaItemSaleEntity.getId(),
                ProductMapper.toDomain(jpaItemSaleEntity.getJpaProduct()),
                jpaItemSaleEntity.getQuantity(),
                SaleMapper.toDomain(jpaItemSaleEntity.getJpaSale()),
                jpaItemSaleEntity.getValue()
        );
    }
}
