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
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nit_ci", unique = true, length = 12)
    private String nitCi;

    @Column(name = "business_name", length = 40)
    private String businessName;

    private String address;

    @Column(name = "phone_number", unique = true, length = 12)
    private Long phoneNumber;

    private Byte state = 1;
}
