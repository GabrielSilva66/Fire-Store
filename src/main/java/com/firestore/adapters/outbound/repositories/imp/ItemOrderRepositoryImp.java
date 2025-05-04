package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaItemOrderEntity;
import com.firestore.adapters.outbound.mapper.ItemOrderMapper;
import com.firestore.adapters.outbound.repositories.JpaItemOrderRepository;
import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.domain.itemOrder.ItemOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ItemOrderRepositoryImp implements ItemOrderRepository {
    private final JpaItemOrderRepository jpaItemOrderRepository;

    public ItemOrderRepositoryImp(JpaItemOrderRepository jpaItemOrderRepository) {
        this.jpaItemOrderRepository = jpaItemOrderRepository;
    }

    @Override
    public ItemOrder save(ItemOrder itemOrder) {
        JpaItemOrderEntity entity = ItemOrderMapper.toEntity(itemOrder);
        JpaItemOrderEntity saved = jpaItemOrderRepository.save(entity);
        return ItemOrderMapper.toDomain(saved);
    }

    @Override
    public List<ItemOrder> findByOrderId(Long orderId) {
        return jpaItemOrderRepository.findByJpaOrderId(orderId).stream()
                .map(ItemOrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemOrder> findAll() {
        return jpaItemOrderRepository.findAll().stream()
                .map(ItemOrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaItemOrderRepository.deleteById(id);
    }

    @Override
    public Optional<ItemOrder> findById(Long id) {
        Optional<JpaItemOrderEntity> entity = jpaItemOrderRepository.findById(id);
        return entity.map(ItemOrderMapper::toDomain);
    }
}
