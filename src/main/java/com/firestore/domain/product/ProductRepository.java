package com.firestore.domain.product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);
    Product findById(Long id);
    void deleteById(Long id);
    List<Product> findAll();

}
