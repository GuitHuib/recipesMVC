package com.RyanSBA.mvc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
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

    @NonNull
    String name;

    String description;

    @OneToMany(targetEntity = RecipeIngredients.class, mappedBy = "recipe")
    Set<RecipeIngredients> recipeIngredients;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipe_id")
    User user;

    @OneToMany(targetEntity = InstructionStep.class)
    List<InstructionStep> instructions;

    boolean vegetarian;

    boolean vegan;

    boolean glutenFree;

    String prepTime;

    String cookTime;

    String serves;

    public void addIngredient(RecipeIngredients recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
    }

    public void removeIngredient(RecipeIngredients recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
    }

    public void addInstruction(InstructionStep step) {
        instructions.add(step);
    }
    public Set<Ingredient> getIngredients() {
        Set<Ingredient> ingredients = new HashSet<>();
        for (RecipeIngredients ingredient : recipeIngredients) {
            ingredients.add(ingredient.ingredient);
        }
        return ingredients;
    }
}
