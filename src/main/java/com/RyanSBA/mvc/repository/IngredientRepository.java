package com.RyanSBA.mvc.repository;

import com.RyanSBA.mvc.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    <Optional>Ingredient findByName(String name);
}
