package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredients;
import com.RyanSBA.mvc.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    List<Recipe> findAllRecipes();
    void saveRecipe(Recipe recipe);
    void addIngredient(Recipe recipe, RecipeIngredients recipeIngredients);
    Recipe findById(int id);
    Set<Recipe> findByUser(User user);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe);
}
