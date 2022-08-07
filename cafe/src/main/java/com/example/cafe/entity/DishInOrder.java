package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DishInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String address;

    private String phoneNumber;

    private String date;

    private int sum;

    public DishInOrder(Menu menu, String address, String phoneNumber, String date, int sum) {
        this.menu = menu;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.sum = sum;
    }

    public DishInOrder() {

    }
}
