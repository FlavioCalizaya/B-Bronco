package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.models.Sale;
import com.infinity.bronco.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("api/v1/sales")
@CrossOrigin()
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;
    @GetMapping
    public ResponseEntity<Iterable<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getSales());
    }
}
