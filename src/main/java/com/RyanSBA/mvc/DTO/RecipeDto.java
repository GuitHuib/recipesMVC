package com.RyanSBA.mvc.DTO;

import com.RyanSBA.mvc.model.Ingredient;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDto {

    int id;

    String email;

    String name;

    String description;

    String instructions;

//    @OneToMany(targetEntity = Ingredient.class)
    Set<Ingredient> ingredients;

    boolean vegetarian;

    boolean vegan;

    boolean glutenFree;

    String prepTime;

    String cookTime;

    int serves;
}
