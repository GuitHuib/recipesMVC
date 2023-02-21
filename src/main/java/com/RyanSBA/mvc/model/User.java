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

    String email;

    String password;

    @OneToMany(targetEntity = Recipe.class)
    Set<Recipe> recipes;

    @OneToOne(targetEntity = ShoppingList.class)
    ShoppingList shoppingList;

}
