package com.infinity.bronco.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalleVenta")
public class SaleDetail {

    @Column( precision = 9, scale = 2)
    private BigDecimal precio;

    private Integer cantidad;

    @Column( precision = 9, scale = 2)
    private BigDecimal importe;

    @ManyToOne
    @JoinColumn(name="idVenta", nullable=false)
    @Id
    private Sale sale;
}
