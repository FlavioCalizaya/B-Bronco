package com.infinity.bronco.models.dto;

import com.infinity.bronco.models.SaleDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaleDetailDTO {

    private  Integer idDetalleVenta;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal importe;
    private  Integer idProducto;
    private  Integer idVenta;



}
