package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository repo;

    @Override
    public List<Ingredient> findAllngredients() {
        return null;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        repo.save(ingredient);
    }

    @Override
    public Recipe findById(int id) {
        return null;
    }

    @Override
    public <Optional> Ingredient findByName(String name) {
        name.trim().toLowerCase();
        Ingredient ingredient = repo.findByName(name);
        return ingredient;
    };

    @Override
    public Set<Recipe> findByRecipe(User user) {
        return null;
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {

    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {

    }
}
