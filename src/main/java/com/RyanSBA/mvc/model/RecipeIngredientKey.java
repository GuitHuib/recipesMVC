package com.RyanSBA.mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RecipeIngredientKey implements Serializable {

    @Column(name="recipe_id")
    int recipeId;

    @Column(name="ingredient_id")
    int ingredientId;
}
