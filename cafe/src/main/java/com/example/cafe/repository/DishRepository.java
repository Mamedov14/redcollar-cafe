package com.example.cafe.repository;

import com.example.cafe.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);
}
