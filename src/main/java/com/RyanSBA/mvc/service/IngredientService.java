package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;

import java.util.List;
import java.util.Set;

public interface IngredientService {

    List<Ingredient> findAllngredients();
    void saveIngredient(Ingredient ingredient);
    Recipe findById(int id);

    <Optional> Ingredient findByName(String name);
    Set<Recipe> findByRecipe(User user);
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);
}
