package com.RyanSBA.mvc.repository;

import com.RyanSBA.mvc.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
