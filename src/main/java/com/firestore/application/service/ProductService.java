package com.firestore.application.service;

import com.firestore.domain.product.Product;
import com.firestore.adapters.outbound.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final JpaProductRepository productRepository;

    @Autowired
    public ProductService(JpaProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        product.setCostPrice(new BigDecimal(product.getCostPrice().toString().replace(",", ".")));
        return productRepository.save(product);
    }
}
