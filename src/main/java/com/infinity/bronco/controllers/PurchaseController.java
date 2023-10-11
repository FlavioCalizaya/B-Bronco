package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/purchase")
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Transactional
    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
        return ResponseEntity.ok(purchaseService.savePurchase(purchase));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Purchase>> findById(@PathVariable("id") Long purchaseId) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(purchaseId));
    }

    @GetMapping
    public ResponseEntity<Iterable<Purchase>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getPurchases());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Long id, @RequestBody Purchase purchase){
        return ResponseEntity.ok(purchaseService.updatePurchaseById(id, purchase));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Purchase> deletePurchase(@PathVariable("id") Long id) {
        return ResponseEntity.ok(purchaseService.deletePurchase(id));
    }
}
