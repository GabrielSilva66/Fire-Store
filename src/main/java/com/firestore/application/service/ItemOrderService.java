package com.firestore.application.service;

import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.adapters.outbound.repositories.JpaItemOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemOrderService {

    private final JpaItemOrderRepository itemOrderRepository;

    @Autowired
    public ItemOrderService(JpaItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    public List<ItemOrder> findAll() {
        return itemOrderRepository.findAll();
    }

    public Optional<ItemOrder> findById(Long id) {
        return itemOrderRepository.findById(id);
    }

    public ItemOrder save(ItemOrder itemOrder) {
        return itemOrderRepository.save(itemOrder);
    }

    public void deleteById(Long id) {
        itemOrderRepository.deleteById(id);
    }
}
