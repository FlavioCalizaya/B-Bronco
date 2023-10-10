package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByEstado(int estado);
}
