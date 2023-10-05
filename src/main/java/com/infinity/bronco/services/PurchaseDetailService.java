package com.infinity.bronco.services;

import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseService {


    private final PurchaseRepository purchaseRepository;

   public Iterable<Purchase> getPurchases() {
        return purchaseRepository.findByState(1);
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Purchase savePurchase(Purchase purchase) {
        if (purchaseRepository.existsById(purchase.getIdPurchase())) {
            purchase.setIdPurchase(null);
        }
        return purchaseRepository.save(purchase);
    }

    public Purchase  updatePurchaseById(Long id, Purchase purchase){
        Purchase updatePurchase = purchaseRepository.findById(id)
                .orElseThrow();

        updatePurchase.setTotal(purchase.getTotal());
        updatePurchase.setDate(purchase.getDate());
        updatePurchase.setProvider(purchase.getProvider());

        purchaseRepository.save(updatePurchase);

        return purchaseRepository.save(updatePurchase);
    }

    public Purchase deletePurchase(Long id) {
        Purchase removePurchase = purchaseRepository.findById(id)
                .orElseThrow();
        removePurchase.setState((byte) 0);
        purchaseRepository.save(removePurchase);
        return purchaseRepository.save(removePurchase);
    }
}
