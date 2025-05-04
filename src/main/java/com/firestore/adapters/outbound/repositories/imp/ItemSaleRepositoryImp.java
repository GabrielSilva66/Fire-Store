package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaItemSaleEntity;
import com.firestore.adapters.outbound.mapper.ItemSaleMapper;
import com.firestore.adapters.outbound.repositories.JpaItemSaleRepository;
import com.firestore.domain.itemSale.ItemSale;
import com.firestore.domain.itemSale.ItemSaleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ItemSaleRepositoryImp implements ItemSaleRepository {

    private final JpaItemSaleRepository jpaItemSaleRepository;

    public ItemSaleRepositoryImp(JpaItemSaleRepository jpaItemSaleRepository) {
        this.jpaItemSaleRepository = jpaItemSaleRepository;
    }

    @Override
    public void save(ItemSale itemSale) {
        JpaItemSaleEntity entity = ItemSaleMapper.toEntity(itemSale);
        jpaItemSaleRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaItemSaleRepository.deleteById(id);
    }

    @Override
    public Optional<ItemSale> findById(Long id) {
        Optional<JpaItemSaleEntity> entity = jpaItemSaleRepository.findById(id);
        return entity.map(ItemSaleMapper::toDomain);
    }

    @Override
    public List<ItemSale> findAll() {
        return jpaItemSaleRepository.findAll().stream()
                .map(ItemSaleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemSale> findBySaleId(Long saleId) {
        return jpaItemSaleRepository.findByJpaSaleId(saleId).stream()
                .map(ItemSaleMapper::toDomain)
                .collect(Collectors.toList());
    }
}
