package com.example.cafe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "coworker_id")
    private Coworker coworker;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String date;

    private String time;

    private int tableNumber;

    private int sum;

    public Order(Coworker coworker, Menu menu, String date, String time, int tableNumber, int sum) {
        this.coworker = coworker;
        this.menu = menu;
        this.date = date;
        this.time = time;
        this.tableNumber = tableNumber;
        this.sum = sum;
    }

    public Order() {

    }
}
