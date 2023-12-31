package com.infinity.bronco.services;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.models.Sale;
import com.infinity.bronco.models.SaleDetail;
import com.infinity.bronco.models.dto.SaleDTO;
import com.infinity.bronco.models.dto.SaleDetailDTO;
import com.infinity.bronco.repositories.ProductRepository;
import com.infinity.bronco.repositories.SaleDetailRepository;
import com.infinity.bronco.repositories.SaleRepository;
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

                // Guarda el detalle de venta
                 SaleDetail newSaleDetail =  saleDetailRepository.save(saleDetail);
                savedSaleDetail.add( newSaleDetail );
            }
        }
        savedSale.setSaleDetails( savedSaleDetail );
        return savedSale;
    }



}
