package com.example.cafe.conrtoller;

import com.example.cafe.entity.Dish;
import com.example.cafe.entity.Ingredient;
import com.example.cafe.entity.IngredientInTheComposition;
import com.example.cafe.service.IngredientInTheCompositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IngredientInTheCompositionController {

    private final IngredientInTheCompositionService ingredientInTheCompositionService;

    @GetMapping("/ingredient-in-the-composition")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<IngredientInTheComposition> ingredientInTheCompositionList = ingredientInTheCompositionService.findAllIngredientInTheComposition();
        model.addAttribute("ingredientComposition", ingredientInTheCompositionList);
        return "ingredient-in-the-composition/ingredient-in-the-composition-list";
    }

    @GetMapping("/ingredient-in-the-composition-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Ingredient> ingredients = ingredientInTheCompositionService.findAllIngredient();
        List<Dish> dishes = ingredientInTheCompositionService.findAllDish();
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("dish", dishes);
        return "ingredient-in-the-composition/ingredient-in-the-composition-create";
    }

    @PostMapping("/ingredient-in-the-composition-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String nameIngredient,
                         @RequestParam String nameDish,
                         @RequestParam int sum) {
        ingredientInTheCompositionService.save(nameIngredient, nameDish, sum);
        return "redirect:/ingredient-in-the-composition";
    }

    @GetMapping("/ingredient-in-the-composition-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable Long id, Model model) {
        List<Ingredient> ingredients = ingredientInTheCompositionService.findAllIngredient();
        List<Dish> dishes = ingredientInTheCompositionService.findAllDish();

        if (ingredientInTheCompositionService.ingredientInTheCompositionList(id).isEmpty()) {
            return "redirect:/ingredient-in-the-composition";
        }
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("dish", dishes);
        model.addAttribute("ingredientComposition", ingredientInTheCompositionService.ingredientInTheCompositionList(id));
        return "ingredient-in-the-composition/ingredient-in-the-composition-update";
    }

    @PostMapping("/ingredient-in-the-composition-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable Long id,
                         @RequestParam String nameIngredient,
                         @RequestParam String nameDish,
                         @RequestParam int sum) {
        ingredientInTheCompositionService.update(id, nameIngredient, nameDish, sum);
        return "redirect:/ingredient-in-the-composition";
    }

    @GetMapping("/ingredient-in-the-composition-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable Long id) {
        ingredientInTheCompositionService.delete(id);
        return "redirect:/ingredient-in-the-composition";
    }
}
