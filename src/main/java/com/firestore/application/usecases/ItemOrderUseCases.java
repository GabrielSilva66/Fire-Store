package com.firestore.application.usecases;

import com.firestore.domain.itemOrder.ItemOrder;

import java.util.List;
import java.util.Optional;

public interface ItemOrderUseCases {
    public List<ItemOrder> findAll();

    public ItemOrder findById(Long id);

    public ItemOrder save(ItemOrder itemOrder);

    public void deleteById(Long id);

}
