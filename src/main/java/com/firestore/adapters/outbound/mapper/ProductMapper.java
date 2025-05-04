package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaProductEntity;
import com.firestore.domain.product.Product;

public class ProductMapper {

    private ProductMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaProductEntity toEntity(Product product) {
        JpaProductEntity jpaProductEntity = new JpaProductEntity();
        jpaProductEntity.setId(product.getId());
        jpaProductEntity.setName(product.getName());
        jpaProductEntity.setCodeBar(product.getCodeBar());
        jpaProductEntity.setCostPrice(product.getCostPrice());
        jpaProductEntity.setStock(product.getStock());
        jpaProductEntity.setPictureUrl(product.getPictureUrl());
        jpaProductEntity.setSalePrice(product.getSalePrice());
        jpaProductEntity.setUnitMeasure(product.getUnitMeasure());

        return jpaProductEntity;
    }

    public static Product toDomain(JpaProductEntity jpaProductEntity) {
        return new Product(
                jpaProductEntity.getCodeBar(),
                jpaProductEntity.getCostPrice(),
                jpaProductEntity.getId(),
                jpaProductEntity.getName(),
                jpaProductEntity.getPictureUrl(),
                jpaProductEntity.getSalePrice(),
                jpaProductEntity.getStock(),
                jpaProductEntity.getUnitMeasure()
        );
    }
}
