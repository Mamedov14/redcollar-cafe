package com.example.cafe.repository;

import com.example.cafe.entity.DishInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishInOrderRepository extends JpaRepository<DishInOrder, Long> {
    List<DishInOrder> findDishInOrderByDate(String date);
}
