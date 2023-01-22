package org.sid.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId; //puisque product ne se trouve pas dans la même BD on déclare de cette manière
    @ManyToOne
    private Bill bill;
    private int quantity;
    private double price;
    private double discount;
}
