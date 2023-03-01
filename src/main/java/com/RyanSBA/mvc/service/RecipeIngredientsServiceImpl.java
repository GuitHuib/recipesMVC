package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredientKey;
import com.RyanSBA.mvc.model.RecipeIngredients;
import com.RyanSBA.mvc.repository.RecipeIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecipeIngredientsServiceImpl implements RecipeIngredientsService{

    private final RecipeIngredientsRepository repo;
    @Autowired
    public RecipeIngredientsServiceImpl(RecipeIngredientsRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<RecipeIngredients> findAllRecipesIngredients() {
        return repo.findAll();
    }

    @Override
    public void saveRecipeIngredient(RecipeIngredients recipeIngredients) {
        repo.save(recipeIngredients);
    }

    @Override
    public RecipeIngredients findById(RecipeIngredientKey key) { return null; }

    @Override
    public void updateRecipeIngredients(RecipeIngredients recipeIngredients) { repo.save(recipeIngredients); }

    @Override
    public void deleteRecipeIngredients(RecipeIngredients recipeIngredients) { repo.delete(recipeIngredients); }
}
