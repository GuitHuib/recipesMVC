package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredients;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.repository.RecipeIngredientsRepository;
import com.RyanSBA.mvc.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{


    final RecipeRepository repo;
    final RecipeIngredientsRepository connRepo;

    @Autowired
    public RecipeServiceImpl(RecipeRepository repo, RecipeIngredientsRepository connRepo) {
        this.repo = repo;
        this.connRepo = connRepo;
    }
    @Override
    public void saveRecipe(Recipe recipe) {
        repo.save(recipe);
    }

    @Override
    public void addIngredient(Recipe recipe, RecipeIngredients recipeIngredients) {
        connRepo.save(recipeIngredients);
        recipe.addIngredient(recipeIngredients);
        repo.save(recipe);
    };

    @Override
    public List<Recipe> findAllRecipes() {
        return repo.findAll();
    }
    @Override
    public Recipe findById(int id) { return repo.findById(id).get(); }

    @Override
    public Set<Recipe> findByUser(User user) {
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) { repo.save(recipe); }

    @Override
    public void deleteRecipe(Recipe recipe) {
        repo.delete(recipe);
    }
}
