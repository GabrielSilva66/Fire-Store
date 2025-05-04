package com.firestore.application.service;

import com.firestore.application.usecases.ProductUseCases;
import com.firestore.domain.product.Product;
import com.firestore.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductUseCases {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        product.setCostPrice(new BigDecimal(product.getCostPrice().toString().replace(",", ".")));
        productRepository.save(product);
    }
}
