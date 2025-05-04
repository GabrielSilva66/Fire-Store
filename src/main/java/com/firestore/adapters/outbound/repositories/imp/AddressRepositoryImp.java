package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.adapters.outbound.mapper.AddressMapper;
import com.firestore.adapters.outbound.repositories.JpaAddressRepository;
import com.firestore.domain.Address.Address;
import com.firestore.domain.Address.AddressRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AddressRepositoryImp implements AddressRepository {


    private final JpaAddressRepository jpaAddressRepository;

    public AddressRepositoryImp(JpaAddressRepository jpaAddressRepository) {
        this.jpaAddressRepository = jpaAddressRepository;
    }

    @Override
    public Optional<Address> findById(Long id) {
        Optional<JpaAddressEntity> entity = this.jpaAddressRepository.findById(id);
        return entity.map(AddressMapper::toDomain);

    }

    @Override
    public Address save(Address address) {
        JpaAddressEntity jpaAddressEntity = AddressMapper.toAddressEntity(address);
        this.jpaAddressRepository.save(jpaAddressEntity);
        return AddressMapper.toDomain(jpaAddressEntity);
    }

    @Override
    public void deleteById(Long id) {
        this.jpaAddressRepository.deleteById(id);
    }

    @Override
    public List<Address> findAll() {
        return  this.jpaAddressRepository.findAll()
                .stream()
                .map(AddressMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public List<Address> findActiveAddress() {
        return this.jpaAddressRepository.findActiveAddress()
                .stream()
                .map(AddressMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Override
    public void updateAddressStatus(Long id, boolean status) {
        this.jpaAddressRepository.updateAddressStatus(id, status);
    }
}
