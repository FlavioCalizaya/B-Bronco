package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByState(int state);
}
