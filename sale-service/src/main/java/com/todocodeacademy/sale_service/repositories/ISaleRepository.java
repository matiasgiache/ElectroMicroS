package com.todocodeacademy.sale_service.repositories;

import com.todocodeacademy.sale_service.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale,Long> {
}
