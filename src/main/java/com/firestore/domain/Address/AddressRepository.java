package com.firestore.domain.Address;

import java.util.List;

public interface AddressRepository {
    Address findById(Long id);
    Address save(Address address);
    void deleteById(Long id);
    List<Address> findAll();
    List<Address> findByUserId(Long userId);

}
