package com.infinity.bronco.services;

import com.infinity.bronco.models.Inventory;
import com.infinity.bronco.models.Product;
import com.infinity.bronco.models.Sale;
import com.infinity.bronco.models.SaleDetail;
import com.infinity.bronco.models.dto.SaleDTO;
import com.infinity.bronco.models.dto.SaleDetailDTO;
import com.infinity.bronco.repositories.InventoryRepository;
import com.infinity.bronco.repositories.ProductRepository;
import com.infinity.bronco.repositories.SaleDetailRepository;
import com.infinity.bronco.repositories.SaleRepository;
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
                // Guarda el detalle de venta
                 SaleDetail newSaleDetail =  saleDetailRepository.save(saleDetail);
                savedSaleDetail.add( newSaleDetail );
            }
        }
        savedSale.setSaleDetails( savedSaleDetail );
        return savedSale;
    }
    public Sale removeSale(Integer id) {
        Optional<Sale> existingProductOptional = saleRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Sale existingSale = existingProductOptional.get();

            int intValue = 0;
            Byte byteValue = (byte) intValue;
            existingSale.setEstado(byteValue);

            return saleRepository.save(existingSale);
        } else {
            throw new EntityNotFoundException("Sale not found with id: " + id);
        }
    }


}
