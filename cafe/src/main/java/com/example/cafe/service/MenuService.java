package com.example.cafe.service;

import com.example.cafe.entity.Dish;
import com.example.cafe.entity.Menu;
import com.example.cafe.repository.DishRepository;
import com.example.cafe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final DishRepository dishRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    public List<Menu> findActualMenu(String date) {
        return menuRepository.findMenuByDate(date);
    }

    public Menu save(String name, double price, String date) {
        Dish dish = dishRepository.findByName(name);
        return menuRepository.save(new Menu(dish, price, date));
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> menuList(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> res = new ArrayList<>();
        menu.ifPresent(res::add);
        return res;
    }

    public Menu update(Long id, String nameDish, double price, String data) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        Dish dish = dishRepository.findByName(nameDish);
        menu.setDish(dish);
        menu.setPrice(price);
        menu.setDate(data);
        return menuRepository.save(menu);
    }
}
