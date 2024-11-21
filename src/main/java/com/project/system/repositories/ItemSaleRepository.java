package com.project.system.repositories;

import com.project.system.models.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSaleRepository extends JpaRepository<Intake, Long> {
}
