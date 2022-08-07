package com.example.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "sum")
    private int sum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contractors_id")
    private Contractor contractor;

    private String date;

    private double price;

    public Purchase(Ingredient ingredient, int sum, Contractor contractor, String date, double price) {
        this.ingredient = ingredient;
        this.sum = sum;
        this.contractor = contractor;
        this.date = date;
        this.price = price;
    }

    public Purchase() {
    }
}
