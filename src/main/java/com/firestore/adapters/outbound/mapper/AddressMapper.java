package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.domain.Address.Address;

public class AddressMapper {

    private AddressMapper() {
        // Private constructor to prevent instantiation
    }

    public static JpaAddressEntity toAddressEntity(Address address){

        if (address == null) {
            return null;
        }

        JpaAddressEntity entity = new JpaAddressEntity();
        entity.setId(address.getId());
        entity.setCountry(address.getCountry());
        entity.setState(address.getState());
        entity.setCity(address.getCity());
        entity.setNeighborhood(address.getNeighborhood());
        entity.setCep(address.getCep());
        entity.setNumber(address.getNumber());

        return entity;
    }

    public static Address toDomain(JpaAddressEntity addressEntity) {
        if(addressEntity == null) {
            return null;
        }

        return new Address(
                addressEntity.getCep(),
                addressEntity.getCity(),
                addressEntity.getId(),
                addressEntity.getCountry(),
                addressEntity.getNeighborhood(),
                addressEntity.getNumber(),
                addressEntity.getState()
        );
    }
}
