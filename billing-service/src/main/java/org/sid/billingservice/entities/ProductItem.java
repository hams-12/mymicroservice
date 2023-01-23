package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.model.Product;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId; //puisque product ne se trouve pas dans la même BD on déclare de cette manière avec la cle etrangère
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Pour ne pas tomber dans la bouble infinie. Quand tu vas me retourner un product item ce n'est pas la peine de me retourner bill
    private Bill bill;
    private int quantity; //cette quantité represente la qté commandée du produit dans la facture
    private double price;
    private double discount;
    @Transient private Product product; //cette annotation permet d'ignorer cet attribut dans la base de données

    public double getAmount(){
        return price*quantity*(1-discount);
    }
}
