package com.infinity.bronco.services;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.repositories.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {


    private final InventoryRepository inventoryRepository;

   public Iterable<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory saveInventory(Inventory inventory) {
        if (inventoryRepository.existsById(inventory.getId())) {
            inventory.setId(null);
        }
        return inventoryRepository.save(inventory);
    }

    public Inventory  updateInventoryById(Long id, Inventory inventory){
        Inventory updateInventory = inventoryRepository.findById(id)
                .orElseThrow();

        updateInventory.setLot(inventory.getLot());
        updateInventory.setPrice(inventory.getPrice());
        updateInventory.setStock(inventory.getStock());
        updateInventory.setProduct(inventory.getProduct());

        inventoryRepository.save(updateInventory);

        return inventoryRepository.save(updateInventory);
    }

    public Inventory deleteInventory(Long id) {
        Inventory removeInventory = inventoryRepository.findById(id)
                .orElseThrow();
        removeInventory.setState((byte) 0);
        inventoryRepository.save(removeInventory);
        return inventoryRepository.save(removeInventory);
    }
}
