package com.firestore.application.usecases;

import com.firestore.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductUseCases {

    public List<Product> findAll();

    public Product findById(Long id);

    public void deleteById(Long id);

    public void save(Product product);


}
