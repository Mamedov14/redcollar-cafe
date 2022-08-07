package com.example.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeApplication {

/*

    todo: dish-create - не работает пост запрос на создание блюда, хз где косяк
    todo: dish-in-order-update - вместо обновления создается новая запись
    todo: ingredient-in-the-composition-update/? - ошибка на фронте 400
    todo: ingredient-in-the-composition-delete/? - ошибка на фронте 400

*/

    public static void main(String[] args) {
        SpringApplication.run(CafeApplication.class, args);
    }
}
