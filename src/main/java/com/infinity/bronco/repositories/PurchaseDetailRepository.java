package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.models.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    List<PurchaseDetail> findByPurchase(Purchase purchase);

}
