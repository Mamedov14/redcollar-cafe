package com.example.cafe.service;

import com.example.cafe.entity.Contractor;
import com.example.cafe.entity.Ingredient;
import com.example.cafe.entity.Purchase;
import com.example.cafe.repository.ContractorRepository;
import com.example.cafe.repository.IngredientRepository;
import com.example.cafe.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final IngredientRepository ingredientRepository;

    private final ContractorRepository contractorRepository;


    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<Ingredient> findAllIngredient() {
        return ingredientRepository.findAll();
    }

    public List<Contractor> findAllContractor() {
        return contractorRepository.findAll();
    }

    public Purchase save(String nameIngredient, int count, String nameContractor, String date, Double price) {
        Ingredient ingredient = ingredientRepository.findByName(nameIngredient);
        Contractor contractor = contractorRepository.findByName(nameContractor);
        return purchaseRepository.save(new Purchase(ingredient, count, contractor, date, price));
    }

    public List<Purchase> purchaseList(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        ArrayList<Purchase> res = new ArrayList<>();
        purchase.ifPresent(res::add);
        return res;
    }

    public Purchase update(Long id, String nameIngredient, int count, String nameContractor, String date, Double price) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        Ingredient ingredient = ingredientRepository.findByName(nameIngredient);
        Contractor contractor = contractorRepository.findByName(nameContractor);
        purchase.setIngredient(ingredient);
        purchase.setSum(count);
        purchase.setContractor(contractor);
        purchase.setDate(date);
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}