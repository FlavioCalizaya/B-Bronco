package com.infinity.bronco.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String primerApellido;

    private String segundoApellido;

    @Column(unique = true, nullable = false)
    private Integer ci;
    private Integer estado = 1;

    @Column(nullable = false)
    private String rol;

    @Column(unique = true, nullable = false)
    private String nameUser;

    @Column(unique = true, nullable = false)
    private String password;

}
