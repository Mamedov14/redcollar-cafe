package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "price_dish")
    private double price;

    private String date;

    @OneToMany(mappedBy = "menu")
    private List<Order> orderList;

    @OneToMany(mappedBy = "menu")
    private List<DishInOrder> dishInOrderList;

    public Menu(Dish dish, double price, String date) {
        this.dish = dish;
        this.price = price;
        this.date = date;
    }

    public Menu() {

    }
}
