package com.infinity.bronco.models.dto;

import com.infinity.bronco.models.Sale;
import com.infinity.bronco.models.SaleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SaleDTO {
    private Sale sale;  // Datos de la venta
    private List<SaleDetailDTO> saleDetails;  // Detalles de la venta
}
