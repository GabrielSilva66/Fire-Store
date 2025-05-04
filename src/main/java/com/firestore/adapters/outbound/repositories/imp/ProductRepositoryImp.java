package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaProductEntity;
import com.firestore.adapters.outbound.mapper.ProductMapper;
import com.firestore.adapters.outbound.repositories.JpaProductRepository;
import com.firestore.domain.product.Product;
import com.firestore.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImp(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public void save(Product product) {
        JpaProductEntity entity = ProductMapper.toEntity(product);
        jpaProductRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<JpaProductEntity> entity = jpaProductRepository.findById(id);
        return entity.map(ProductMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return jpaProductRepository.existsById(id);
    }
}
