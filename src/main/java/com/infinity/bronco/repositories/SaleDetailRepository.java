package com.infinity.bronco.repositories;

import com.infinity.bronco.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {

}
