package com.firestore.application.service;

import com.firestore.domain.itemOrder.ItemOrder;
import com.firestore.adapters.outbound.repositories.JpaItemOrderRepository;
import com.firestore.domain.itemOrder.ItemOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemOrderService {

    private final ItemOrderRepository itemOrderRepository;

    @Autowired
    public ItemOrderService(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    public List<ItemOrder> findAll() {
        return itemOrderRepository.findAll();
    }

    public ItemOrder findById(Long id) {
        return itemOrderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ItemOrder not found"));
    }

    public ItemOrder save(ItemOrder itemOrder) {
        return itemOrderRepository.save(itemOrder);
    }

    public void deleteById(Long id) {
        itemOrderRepository.deleteById(id);
    }
}
