package com.infinity.bronco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nit_ci", unique = true, length = 12)
    private String nitCi;

    @Column(name = "business_name", length = 40)
    private String businessName;

    @Column(name = "phone_number", unique = true, length = 12)
    private Long phoneNumber;

    @Column(name = "estado", unique = true, length = 12)
    private Long estado;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Servicey> service;
}
