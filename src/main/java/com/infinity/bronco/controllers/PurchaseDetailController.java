package com.infinity.bronco.controllers;

import com.infinity.bronco.models.PurchaseDetail;
import com.infinity.bronco.services.PurchaseDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/purchasedetail")
@CrossOrigin(origins = "*")
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @PostMapping
    public ResponseEntity<PurchaseDetail> createPurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {

        System.out.println(purchaseDetail);

        return ResponseEntity.ok(purchaseDetailService.savePurchaseDetail(purchaseDetail));
    }


    @GetMapping
    public ResponseEntity<Iterable<PurchaseDetail>> getAllPurchaseDetails() {
        return ResponseEntity.ok(purchaseDetailService.getPurchaseDetails());
    }
 }
