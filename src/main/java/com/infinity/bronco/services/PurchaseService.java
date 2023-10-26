package com.infinity.bronco.services;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.models.Provider;
import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.models.PurchaseDetail;
import com.infinity.bronco.repositories.InventoryRepository;
import com.infinity.bronco.repositories.PurchaseDetailRepository;
import com.infinity.bronco.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PurchaseService {


    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final InventoryRepository inventoryRepository;

   public Iterable<Purchase> getPurchases() {
        return purchaseRepository.findByState(1);
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Transactional
    public Purchase savePurchase(Purchase purchase) {

        Purchase purchasePersist = new Purchase();
        PurchaseDetail purchaseDetailPersist = new PurchaseDetail();

        //CREATE PURCHASE OBJECT
        purchasePersist.setDate(purchase.getDate());
        purchasePersist.setTotal(purchase.getTotal());
        purchasePersist.setProvider(purchase.getProvider());

        Purchase purchase1 = purchaseRepository.save(purchasePersist);


        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetails()) {
            purchaseDetailPersist.setId(0);
            purchaseDetailPersist.setPurchase(purchase1);
            purchaseDetailPersist.setProduct(purchaseDetail.getProduct());
            purchaseDetailPersist.setAmount(purchaseDetail.getAmount());
            purchaseDetailPersist.setPrice(purchaseDetail.getPrice());
            purchaseDetailPersist.setQuantity(purchaseDetail.getQuantity());
            Inventory inventory = inventoryRepository.save(purchaseDetail.getInventory());
            purchaseDetailPersist.setInventory(inventory);

            PurchaseDetail p = purchaseDetailRepository.save(purchaseDetailPersist);
        }

        return purchasePersist;

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
        List<PurchaseDetail> purchaseDetail = purchaseDetailRepository.findByPurchase(removePurchase);
        removePurchase.setState((byte) 0);

        for (PurchaseDetail p: purchaseDetail) {
            Inventory inventory = inventoryRepository.findById(Long.valueOf(p.getId())).get();
            p.setState((byte) 0);
            inventory.setState(((byte) 0));
            purchaseDetailRepository.save(p);
            inventoryRepository.save(inventory);
        }

        purchaseRepository.save(removePurchase);
        return purchaseRepository.save(removePurchase);
    }
}
