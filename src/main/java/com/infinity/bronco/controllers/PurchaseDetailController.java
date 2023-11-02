package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.models.Purchase;
import com.infinity.bronco.models.PurchaseDetail;
import com.infinity.bronco.response.ResponseHandler;
import com.infinity.bronco.services.PurchaseDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/purchasedetail")
@CrossOrigin(origins = "*")
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {

        try {
            //Purchase purchase = purchaseDetail.getPurchase();
            PurchaseDetail result = purchaseDetailService.savePurchaseDetail(purchaseDetail);
            if (result != null)
            {
                return ResponseHandler.generateResponse( HttpStatus.CREATED, "Compra creado con Exito!", result);
            }else {
                return ResponseHandler.generateResponse( HttpStatus.BAD_REQUEST, "[Register Error:], Compra no registrado", null);
            }

        }catch ( Exception e ){
            e.printStackTrace();
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
        }
    }


    @GetMapping
    public ResponseEntity<Iterable<PurchaseDetail>> getAllPurchaseDetails() {

        return ResponseEntity.ok(purchaseDetailService.getPurchaseDetails());
    }
 }
