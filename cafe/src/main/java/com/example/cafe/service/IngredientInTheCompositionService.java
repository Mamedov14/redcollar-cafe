package com.example.cafe.service;

import com.example.cafe.entity.Dish;
import com.example.cafe.entity.Ingredient;
import com.example.cafe.entity.IngredientInTheComposition;
import com.example.cafe.repository.DishRepository;
import com.example.cafe.repository.IngredientInTheCompositionRepository;
import com.example.cafe.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientInTheCompositionService {

    private final IngredientInTheCompositionRepository ingredientInTheCompositionRepository;

    private final DishRepository dishRepository;

    private final IngredientRepository ingredientRepository;


    public List<IngredientInTheComposition> findAllIngredientInTheComposition() {
        return ingredientInTheCompositionRepository.findAll();
    }

    public List<Dish> findAllDish() {
        return dishRepository.findAll();
    }

    public List<Ingredient> findAllIngredient() {
        return ingredientRepository.findAll();
    }

    public IngredientInTheComposition save(String nameIngredient, String nameDish, int count) {
        Ingredient ingredient = ingredientRepository.findByName(nameIngredient);
        Dish dish = dishRepository.findByName(nameDish);
        return ingredientInTheCompositionRepository.save(new IngredientInTheComposition(ingredient, dish, count));
    }

    public List<IngredientInTheComposition> ingredientInTheCompositionList(Long id) {
        Optional<IngredientInTheComposition> ingredientInTheComposition = ingredientInTheCompositionRepository.findById(id);
        ArrayList<IngredientInTheComposition> res = new ArrayList<>();
        ingredientInTheComposition.ifPresent(res::add);
        return res;
    }

    public IngredientInTheComposition update(Long id, String nameIngredient, String nameDish, int count) {
        IngredientInTheComposition ingredientInTheComposition = ingredientInTheCompositionRepository.findById(id).orElseThrow();
        Ingredient ingredient = ingredientRepository.findByName(nameIngredient);
        Dish dish = dishRepository.findByName(nameDish);
        ingredientInTheComposition.setIngredient(ingredient);
        ingredientInTheComposition.setDish(dish);
        ingredientInTheComposition.setSum(count);
        return ingredientInTheCompositionRepository.save(ingredientInTheComposition);
    }

    public void delete(Long id) {
        ingredientInTheCompositionRepository.deleteById(id);
    }


}
