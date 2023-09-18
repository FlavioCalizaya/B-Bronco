package com.infinity.bronco.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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



}
