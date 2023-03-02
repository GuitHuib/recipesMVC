package com.RyanSBA.mvc.repository;

import com.RyanSBA.mvc.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("SELECT i FROM Ingredient i WHERE i.name =?1")
    Optional<Ingredient> findByName(String name);
}
