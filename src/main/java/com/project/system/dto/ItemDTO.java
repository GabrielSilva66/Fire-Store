package com.project.system.dto;

import com.project.system.models.Product;

public record ItemDTO(
        Product product,
        Integer quantity,
        Double value
){}
