package com.RyanSBA.mvc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.HashSet;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String instructions;

    String description;

    @OneToMany(targetEntity = RecipeIngredients.class, mappedBy = "recipe")
    Set<RecipeIngredients> recipeIngredients;

    boolean vegetarian;

    boolean vegan;

    boolean glutenFree;

    String prepTime;

    String cookTime;

    int serves;

    public void addIngredient(RecipeIngredients recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
    }

    public void removeIngredient(RecipeIngredients recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
    }

    public Set<Ingredient> getIngredients() {
        Set<Ingredient> ingredients = new HashSet<>();
        for (RecipeIngredients ingredient : recipeIngredients) {
            ingredients.add(ingredient.ingredient);
        }
        return ingredients;
    }
}
