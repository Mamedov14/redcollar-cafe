package com.example.cafe.service;

import com.example.cafe.entity.Dish;
import com.example.cafe.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;


    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    public Dish saveDish(String name, String unit) {
        return save(new Dish(name, unit));
    }

    public Dish update(Long id, String nameDish, String unit) {
        Dish dish = findById(id);
        dish.setName(nameDish);
        dish.setUnit(unit);
        return save(dish);
    }

    public List<Dish> dishList(long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add);
        return res;
    }
}
