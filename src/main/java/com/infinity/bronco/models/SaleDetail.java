package com.infinity.bronco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "DetalleVenta")
public class SaleDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer idDetalleVenta;

    @Column( precision = 9, scale = 2)
    private BigDecimal precio;

    private Integer cantidad;

    @Column( precision = 9, scale = 2)
    private BigDecimal importe;

    @ManyToOne
    @JoinColumn(name="idVenta", nullable=false)
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @JoinColumn(name="idProducto", nullable=false)
    private Product product;
}
