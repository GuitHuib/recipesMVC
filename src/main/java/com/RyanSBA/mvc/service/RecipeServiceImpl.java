package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{


    @Autowired
    RecipeRepository repo;
    @Override
    public void saveRecipe(Recipe recipe) {
        repo.save(recipe);
    }

    @Override
    public List<Recipe> findAllRecipes() {
        return repo.findAll();
    }
    @Override
    public Recipe findById(int id) {
        Recipe recipe = repo.findById(id).get();
        return recipe;
    }

    @Override
    public Set<Recipe> findByUser(User user) {
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public void deleteRecipe(Recipe recipe) {

    }
}
