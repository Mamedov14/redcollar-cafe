package com.example.cafe.service;

import com.example.cafe.entity.Ingredient;
import com.example.cafe.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient save(String nameIngredient, String measure) {
        return ingredientRepository.save(new Ingredient(nameIngredient, measure));
    }

    public List<Ingredient> ingredientList(long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        ArrayList<Ingredient> res = new ArrayList<>();
        ingredient.ifPresent(res::add);
        return res;
    }

    public Ingredient update(Long id, String nameIngredient, String unit) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
        ingredient.setName(nameIngredient);
        ingredient.setUnit(unit);
        return ingredientRepository.save(ingredient);
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
