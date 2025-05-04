package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.adapters.outbound.entities.JpaEmployeeEntity;
import com.firestore.domain.Address.Address;
import com.firestore.domain.employee.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaEmployeeEntity toEntity(Employee employee){

        JpaEmployeeEntity jpaEmployeeEntity = new JpaEmployeeEntity();
        jpaEmployeeEntity.setId(employee.getId());
        jpaEmployeeEntity.setName(employee.getName());
        jpaEmployeeEntity.setEmail(employee.getEmail());
        jpaEmployeeEntity.setTelephone(employee.getTelephone());
        jpaEmployeeEntity.setActive(employee.isActive());
        jpaEmployeeEntity.setRole(employee.getRole());
        jpaEmployeeEntity.setJpaAddress(AddressMapper.toAddressEntity(employee.getAddress()));
        return jpaEmployeeEntity;

    }

    public static Employee toDomain(JpaEmployeeEntity jpaEmployeeEntity) {
        return new Employee(
                AddressMapper.toDomain(jpaEmployeeEntity.getJpaAddress()),
                jpaEmployeeEntity.getEmail(),
                jpaEmployeeEntity.getId(),
                jpaEmployeeEntity.isActive(),
                jpaEmployeeEntity.getName(),
                jpaEmployeeEntity.getRole(),
                jpaEmployeeEntity.getTelephone()
        );
    }
}

