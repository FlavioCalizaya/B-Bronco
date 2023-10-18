package com.infinity.bronco.services;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.models.Product;
import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.models.PurchaseDetail;
import com.infinity.bronco.repositories.InventoryRepository;
import com.infinity.bronco.repositories.ProductRepository;
import com.infinity.bronco.repositories.PurchaseDetailRepository;
import com.infinity.bronco.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseDetailService {


    private final PurchaseDetailRepository purchaseDetailRepository;
    private final PurchaseRepository purchaseRepository;
    private final InventoryRepository inventoryRepository;

   public Iterable<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    @Transactional
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {

       //Purchase purchase = purchaseDetail.getPurchase();
       Product productList= purchaseDetail.getProduct();
       Inventory inventory = purchaseDetail.getInventory();

       PurchaseDetail purchasePersist = new PurchaseDetail();
       purchasePersist.setPrice(purchaseDetail.getPrice());
       purchasePersist.setQuantity(purchaseDetail.getQuantity());
       purchasePersist.setAmount(purchaseDetail.getAmount());
       purchasePersist.setInventory(inventory);
       //purchasePersist.setPurchase(purchase);
       purchasePersist.setProduct(productList);

        System.out.println("Estoy aqui>"+ productList);

        inventoryRepository.save(inventory);

        return purchaseDetailRepository.save(purchasePersist);

    }

}
