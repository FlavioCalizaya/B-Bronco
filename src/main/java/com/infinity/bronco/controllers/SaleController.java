package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.models.Sale;
import com.infinity.bronco.models.dto.SaleDTO;
import com.infinity.bronco.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/nueva")
    public ResponseEntity<Sale> crearVentaConDetalles(@RequestBody SaleDTO ventaDTO) {
       Sale sale =  saleService.saveSale(ventaDTO);
        return ResponseEntity.ok( sale );
    }
    @PutMapping( path = "/remove/{id}")
    public ResponseEntity<Sale> removeSale( @PathVariable Integer id) {
        Sale updated = saleService.removeSale(id);
        return ResponseEntity.ok(updated);
    }
}
