package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String unit;

    @OneToMany(mappedBy = "dish")
    private List<Menu> menuList;

    @OneToMany(mappedBy = "dish")
    private List<IngredientInTheComposition> ingredientInTheCompositionList;

    public Dish(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Dish() {

    }

}
