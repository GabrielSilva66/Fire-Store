package com.firestore.application.usecases;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.domain.Address.Address;

public interface AddressUseCases {
    public Address getAddressFromEntity(String entityType, Long entityId);
    public void saveAddressAndBindToEntity(Address address, String entityType, Long entityId);
}
