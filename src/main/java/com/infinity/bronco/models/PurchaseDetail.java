package com.infinity.bronco.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {

    private Double price;

    private Integer quantity;

    private Double amount;

    @JoinColumn(name = "id_purchase", referencedColumnName = "id_purchase")
    @ManyToOne
    private Purchase purchase;

    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "id_inventory", referencedColumnName = "id_inventory")
    @ManyToOne
    private Inventory inventory;

}
