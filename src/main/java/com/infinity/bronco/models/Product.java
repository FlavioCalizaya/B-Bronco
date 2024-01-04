package com.infinity.bronco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProducto;
    private Integer categoria;
    private String codigo;
    private String imagen;
    private String nombreProducto;
    private double precioVenta;
    private Integer estado;
    private Double alto;
    private Double ancho;
    private Double espesor;
    private String marca;
    private String tipo;
    private String Descripcion;


    @OneToMany(mappedBy="product")
    @JsonIgnore
    private Set<SaleDetail> saleDetails;


}
