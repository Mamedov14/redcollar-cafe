package com.example.cafe.repository;

import com.example.cafe.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByDishNameAndDate(String name, String date);
    List<Menu> findMenuByDate(String date);
}
