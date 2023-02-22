package com.RyanSBA.mvc.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredients {

    @EmbeddedId
    RecipeIngredientKey id;

    @ManyToOne
    @MapsId("recipeId")
    Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    Ingredient ingredient;
    private double amount;

    private String type;

    public RecipeIngredients (Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientKey(recipe.getId(), ingredient.getId());
    }

    public RecipeIngredients (Recipe recipe, Ingredient ingredient, double amount, String type) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientKey(recipe.getId(), ingredient.getId());
        this.amount = amount;
        this.type = getType();
    }

}
