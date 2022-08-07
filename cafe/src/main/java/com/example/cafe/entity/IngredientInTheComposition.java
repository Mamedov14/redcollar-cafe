package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class IngredientInTheComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private int sum;

    public IngredientInTheComposition(Ingredient ingredient, Dish dish, int sum) {
        this.ingredient = ingredient;
        this.dish = dish;
        this.sum = sum;
    }

    public IngredientInTheComposition() {

    }
}
