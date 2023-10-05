package com.infinity.bronco.services;

import com.infinity.bronco.models.PurchaseDetail;
import com.infinity.bronco.repositories.PurchaseDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseDetailService {


    private final PurchaseDetailRepository purchaseDetailRepository;

   public Iterable<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {

       return purchaseDetailRepository.save(purchaseDetail);
    }

}
