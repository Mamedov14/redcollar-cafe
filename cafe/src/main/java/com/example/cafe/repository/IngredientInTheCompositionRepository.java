package com.example.cafe.repository;

import com.example.cafe.entity.IngredientInTheComposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInTheCompositionRepository extends JpaRepository<IngredientInTheComposition, Long> {
}
