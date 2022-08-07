package com.example.cafe.conrtoller;

import com.example.cafe.entity.Dish;
import com.example.cafe.service.DishService;
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
public class DishController {

    private final DishService dishService;

    @GetMapping("/dish")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Dish> dishes = dishService.findAll();
        model.addAttribute("dish", dishes);
        return "dish/dish-list";
    }

    @GetMapping("/dish-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createDishForm() {
        return "dish/dish-create";
    }

    @PostMapping("/dish-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createDish(@RequestParam String name,
                             @RequestParam String unit) {
        dishService.saveDish(name, unit);
        return "redirect:/dish";
    }

    @GetMapping("/dish-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String dishDelete(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dish";
    }

    @GetMapping("/dish-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateDishForm(@PathVariable Long id, Model model) {
        if (dishService.dishList(id).isEmpty()) {
            return "redirect:/dish";
        }
        model.addAttribute("dish", dishService.dishList(id));
        return "dish/dish-update";
    }

    @PostMapping("/dish-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateDish(@PathVariable long id,
                             @RequestParam String name,
                             @RequestParam String unit) {
        dishService.update(id, name, unit);
        return "redirect:/dish";
    }
}
