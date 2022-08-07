package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String unit;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientInTheComposition> ingredientInTheCompositionList;

    @OneToMany(mappedBy = "ingredient")
    private List<Purchase> purchaseList;

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Ingredient() {

    }
}
