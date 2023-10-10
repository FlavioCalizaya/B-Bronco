package com.infinity.bronco.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVenta;

    @Column(name = "total", precision = 9, scale = 2)
    private BigDecimal total;

    @Column(name = "estado")
    private Byte estado;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "nro_correlativo")
    private Integer nroCorrelativo;

    @OneToMany(mappedBy="sale")
    private Set<SaleDetail> saleDetails;

}
