package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {

        return ResponseEntity.ok( inventoryService.saveInventory(inventory));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Inventory>> findById(@PathVariable("id") Long inventoryId) {
        return ResponseEntity.ok(inventoryService.getInventoryById(inventoryId));
    }

    @GetMapping
    public ResponseEntity<Iterable<Inventory>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getInventories());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") Long id, @RequestBody Inventory inventory){
        return ResponseEntity.ok(inventoryService.updateInventoryById(id, inventory));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventoryService.deleteInventory(id));
    }
}
