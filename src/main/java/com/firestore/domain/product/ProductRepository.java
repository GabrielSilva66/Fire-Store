package com.firestore.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    List<Product> findAll();
    boolean existsById(Long id);

}
