package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository repo;

    @Override
    public List<Ingredient> findAllIngredients() {
        return repo.findAll();
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        repo.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(int id) { return repo.findById(id); }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return repo.findByName(name.trim().toLowerCase());
    };

    @Override
    public void updateIngredient(Ingredient ingredient) { repo.save(ingredient); }

    @Override
    public void deleteIngredient(Ingredient ingredient) { repo.delete(ingredient); }
}
