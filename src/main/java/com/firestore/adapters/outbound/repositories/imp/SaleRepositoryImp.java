package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaSaleEntity;
import com.firestore.adapters.outbound.mapper.SaleMapper;
import com.firestore.adapters.outbound.repositories.JpaSaleRepository;
import com.firestore.domain.sale.Sale;
import com.firestore.domain.sale.SaleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SaleRepositoryImp implements SaleRepository {

    private final JpaSaleRepository jpaSaleRepository;

    public SaleRepositoryImp(JpaSaleRepository jpaSaleRepository) {
        this.jpaSaleRepository = jpaSaleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        JpaSaleEntity entity = SaleMapper.toEntity(sale);
        JpaSaleEntity saved = jpaSaleRepository.save(entity);
        return SaleMapper.toDomain(saved);
    }

    @Override
    public Optional<Sale> findById(Long id) {
       Optional<JpaSaleEntity> entity = jpaSaleRepository.findById(id);
        return entity.map(SaleMapper::toDomain);
    }

    @Override
    public List<Sale> findAll() {
        return jpaSaleRepository.findAll().stream()
                .map(SaleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaSaleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaSaleRepository.existsById(id);
    }
}
