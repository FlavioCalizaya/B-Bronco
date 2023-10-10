package com.infinity.bronco.services;

import com.infinity.bronco.models.Sale;
import com.infinity.bronco.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Iterable<Sale> getSales() {
        return saleRepository.findByEstado(1);
    }
}
