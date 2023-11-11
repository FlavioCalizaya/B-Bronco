package com.infinity.bronco.services;

import com.infinity.bronco.models.*;
import com.infinity.bronco.models.dto.SaleDTO;
import com.infinity.bronco.models.dto.SaleDetailDTO;
import com.infinity.bronco.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class SaleService {


    private final SaleRepository saleRepository;

    private SaleRepository ventaRepository;
    private SaleDetailRepository saleDetailRepository;
    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;
    private ClientRepository clientRepository;
    public Iterable<Sale> getSales() {
        return saleRepository.findByEstado(1);
    }


    @Transactional
    public Sale saveSale(SaleDTO ventaDTO) {
        Sale sale = ventaDTO.getSale();
        List<SaleDetailDTO> saleDetails = ventaDTO.getSaleDetails();




        // Guarda la venta y obtiene su id
        Sale savedSale = ventaRepository.save(sale);

        List<SaleDetail> savedSaleDetail = new ArrayList<>();
        // Itera sobre los detalles y guarda cada uno
        for (SaleDetailDTO elementDetail : saleDetails) {
            Product product = productRepository.findById(elementDetail.getIdProducto()).orElse(null);
            Inventory inventory = inventoryRepository.findById(elementDetail.getIdInventario() ).orElse(null);

            if (product != null) {
                // Crear un nuevo SaleDetail
                SaleDetail saleDetail = new SaleDetail();

                if ( saleDetailRepository.existsById( elementDetail.getIdDetalleVenta() )  ) {
                    saleDetail.setIdDetalleVenta(null);
                } else {
                    saleDetail.setIdDetalleVenta(elementDetail.getIdDetalleVenta());
                }
                saleDetail.setIdDetalleVenta(elementDetail.getIdDetalleVenta());
                saleDetail.setProduct(product);
                saleDetail.setSale(savedSale);
                saleDetail.setPrecio(elementDetail.getPrecio());
                saleDetail.setCantidad(elementDetail.getCantidad());
                saleDetail.setImporte(elementDetail.getImporte());
                saleDetail.setInventory( inventory );


                int soldQuantity = elementDetail.getCantidad();
                int currentStock = inventory.getStock();
                inventory.setStock(currentStock - soldQuantity);


                inventoryRepository.save(inventory);

                // Guarda el detalle de venta
                 SaleDetail newSaleDetail =  saleDetailRepository.save(saleDetail);
                savedSaleDetail.add( newSaleDetail );
            }
        }
        savedSale.setSaleDetails( savedSaleDetail );
        return savedSale;
    }
    public Sale removeSale(Integer id) {
        Optional<Sale> existingSaleOptional = saleRepository.findById(id);

        if (existingSaleOptional.isPresent()) {
            Sale existingSale = existingSaleOptional.get();

            // Check if the sale is already removed
            if (existingSale.getEstado() == 0) {
                throw new EntityNotFoundException("Sale is already removed with id: " + id);
            }

            // Iterate through the SaleDetails associated with the sale
            for (SaleDetail saleDetail : existingSale.getSaleDetails()) {
                Inventory inventory = saleDetail.getInventory();

                if (inventory != null) {
                    // Increase the stock of the associated Inventory
                    int soldQuantity = saleDetail.getCantidad();
                    int currentStock = inventory.getStock();
                    inventory.setStock(currentStock + soldQuantity);

                    // Save the updated Inventory
                    inventoryRepository.save(inventory);
                }
            }

            // Set the sale as removed
            existingSale.setEstado((byte) 0);

            return saleRepository.save(existingSale);
        } else {
            throw new EntityNotFoundException("Sale not found with id: " + id);
        }
    }



}
