package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredientKey;
import com.RyanSBA.mvc.model.RecipeIngredients;
import java.util.List;
import java.util.Set;

public interface RecipeIngredientsService {
    List<RecipeIngredients> findAllRecipesIngredients();
    void saveRecipeIngredient(RecipeIngredients recipeIngredients);
    RecipeIngredients findById(RecipeIngredientKey key);
    void updateRecipeIngredients(RecipeIngredients recipeIngredients);
    void deleteRecipeIngredients(RecipeIngredients recipeIngredients);
}
