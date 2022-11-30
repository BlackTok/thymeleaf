package ru.gb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    public Product() {
    }

    public Product(String title, int cost, double purchasePrice) {
        this.title = title;
        this.cost = cost;
        this.purchasePrice = purchasePrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @Column(name = "purchase_price")
    private double purchasePrice;
}
