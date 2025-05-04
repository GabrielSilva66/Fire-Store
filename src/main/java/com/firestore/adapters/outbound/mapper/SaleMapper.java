package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaSaleEntity;
import com.firestore.domain.sale.Sale;

public class SaleMapper {
    private SaleMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaSaleEntity toEntity(Sale sale) {
        JpaSaleEntity jpaSaleEntity = new JpaSaleEntity();
        jpaSaleEntity.setId(sale.getId());
        jpaSaleEntity.setDate(sale.getDate());
        jpaSaleEntity.setTotalValue(sale.getTotalValue());
        jpaSaleEntity.setJpaCustomer(CustomerMapper.toEntity(sale.getCustomer()));
        jpaSaleEntity.setObservation(sale.getObservation());
        jpaSaleEntity.setTotalQuantity(sale.getTotalQuantity());
        jpaSaleEntity.setJpaEmployee(EmployeeMapper.toEntity(sale.getEmployee()));

        return jpaSaleEntity;
    }

    public static Sale toDomain(JpaSaleEntity jpaSaleEntity) {
        return new Sale(
                CustomerMapper.toDomain(jpaSaleEntity.getJpaCustomer()),
                jpaSaleEntity.getDate(),
                EmployeeMapper.toDomain(jpaSaleEntity.getJpaEmployee()),
                jpaSaleEntity.getId(),
                jpaSaleEntity.getObservation(),
                jpaSaleEntity.getTotalQuantity(),
                jpaSaleEntity.getTotalValue()
        );
    }
}
