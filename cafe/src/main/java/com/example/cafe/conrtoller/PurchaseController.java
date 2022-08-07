package com.example.cafe.conrtoller;

import com.example.cafe.entity.Contractor;
import com.example.cafe.entity.Ingredient;
import com.example.cafe.entity.Purchase;
import com.example.cafe.service.PurchaseService;
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
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/purchase")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Purchase> purchaseList = purchaseService.findAllPurchase();
        model.addAttribute("purchase", purchaseList);
        return "purchase/purchase-list";
    }

    @GetMapping("/purchase-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Ingredient> ingredients = purchaseService.findAllIngredient();
        List<Contractor> contractors = purchaseService.findAllContractor();
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("contractor", contractors);
        return "purchase/purchase-create";
    }

    @PostMapping("/purchase-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String nameIngredient,
                         @RequestParam int count,
                         @RequestParam String nameContractor,
                         @RequestParam String date,
                         @RequestParam Double price) {
        purchaseService.save(nameIngredient, count, nameContractor, date, price);
        return "redirect:/purchase";
    }

    @GetMapping("/purchase-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable Long id, Model model) {
        List<Ingredient> ingredients = purchaseService.findAllIngredient();
        List<Contractor> contractors = purchaseService.findAllContractor();

        if (purchaseService.purchaseList(id).isEmpty()) {
            return "redirect:/purchase";
        }
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("contractor", contractors);
        model.addAttribute("purchase", purchaseService.purchaseList(id));
        return "purchase/purchase-update";
    }

    @PostMapping("/purchase-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable Long id,
                         @RequestParam String nameIngredient,
                         @RequestParam int count,
                         @RequestParam String nameContractor,
                         @RequestParam String date,
                         @RequestParam Double price) {
        purchaseService.update(id, nameIngredient, count, nameContractor, date, price);
        return "redirect:/purchase";
    }

    @GetMapping("/purchase-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable Long id) {
        purchaseService.delete(id);
        return "redirect:/purchase";
    }
}
