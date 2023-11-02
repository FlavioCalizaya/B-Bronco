package com.infinity.bronco.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Venta")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVenta;

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
    private List<SaleDetail> saleDetails;

}
