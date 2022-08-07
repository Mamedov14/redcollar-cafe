package com.example.cafe.conrtoller;

import com.example.cafe.entity.Ingredient;
import com.example.cafe.service.IngredientService;
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
public class IngredientController {

    private final IngredientService ingredientService;


    @GetMapping("/ingredient")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Ingredient> ingredientList = ingredientService.findAll();
        model.addAttribute("ingredient", ingredientList);
        return "ingredient/ingredient-list";
    }

    @GetMapping("/ingredient-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm() {
        return "ingredient/ingredient-create";
    }

    @PostMapping("/ingredient-create")
    public String create(@RequestParam String name,
                         @RequestParam String unit) {
        ingredientService.save(name, unit);
        return "redirect:/ingredient";
    }

    @GetMapping("/ingredient-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable Long id, Model model) {
        if (ingredientService.ingredientList(id).isEmpty()) {
            return "redirect:/ingredient";
        }
        model.addAttribute("ingredient", ingredientService.ingredientList(id));
        return "ingredient/ingredient-update";
    }

    @PostMapping("/ingredient-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String unit) {
        ingredientService.update(id, name, unit);
        return "redirect:/ingredient";
    }

    @GetMapping(("/ingredient-delete/{id}"))
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return "redirect:/ingredient";
    }

}
