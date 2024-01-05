package com.infinity.bronco.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    //private Integer idAssignedMaintenanceUser;

    private String statusMaintenance;

    private Date dateEnd;


    @Column(name = "created_att",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAtt;




    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_assigned_maintenance_user")
    private User assignedMaintenanceUser;


    /*@ManyToOne
    @JoinColumn(name = "id_user")
    private User user;*/
}
