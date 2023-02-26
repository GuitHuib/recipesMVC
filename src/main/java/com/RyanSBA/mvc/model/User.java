package com.RyanSBA.mvc.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String email;
    String password;
    @OneToMany(targetEntity = Recipe.class)
    Set<Recipe> recipes;
    @OneToMany(targetEntity = Ingredient.class)
    Set<Ingredient> ingredients;

    public void addToShoppingList(Set<Ingredient> newIngredients) {
        ingredients.addAll(newIngredients);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
