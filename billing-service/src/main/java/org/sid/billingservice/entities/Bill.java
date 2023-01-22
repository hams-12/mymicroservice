package org.sid.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId; //puisque customer ne se trouve pas dans la même BD on procède de cette manière
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;
}
