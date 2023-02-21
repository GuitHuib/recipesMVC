package com.RyanSBA.mvc.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String instructions;

    @OneToMany(targetEntity = Ingredient.class)
    Set<Ingredient> ingredients;

    boolean vegetarian;

    boolean vegan;

    boolean glutenFree;

    String prepTime;

    String cookTime;

    int serves;
}
