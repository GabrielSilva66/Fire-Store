package com.firestore.dto;

import com.firestore.domain.product.Product;

public record ItemDTO(
        Product product,
        Integer quantity,
        Double value
){}
