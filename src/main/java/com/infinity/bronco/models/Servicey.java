package com.infinity.bronco.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Servicey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String serviceType;

    private String description;

    private Double amount;

    private Integer idUser;

    private Integer estado;

    private Date dateInit;

    private Integer idAssignedMaintenanceUser;

    private Boolean statusMaintenance;

    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

}
