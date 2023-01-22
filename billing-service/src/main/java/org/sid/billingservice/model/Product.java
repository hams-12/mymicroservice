package org.sid.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity; //cette quantité represente la qté du produit en stock
}
