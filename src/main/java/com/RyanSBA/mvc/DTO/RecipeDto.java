package com.RyanSBA.mvc.DTO;

import com.RyanSBA.mvc.model.Ingredient;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDto {

    int id;
    String email;
    @NonNull
    String name;
    String description;
    String instructions;
    Set<Ingredient> ingredients;
    boolean vegetarian;
    boolean vegan;
    boolean glutenFree;
    String prepTime;
    String cookTime;
    String serves;

    public RecipeDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
