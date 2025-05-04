package com.firestore.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findById(Long id);
    Address save(Address address);
    void deleteById(Long id);
    List<Address> findAll();
    List<Address> findActiveAddress();

    void updateAddressStatus(Long id, boolean status);


}
