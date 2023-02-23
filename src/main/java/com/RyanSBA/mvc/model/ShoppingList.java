//package com.RyanSBA.mvc.model;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import java.util.Set;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class ShoppingList {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//
//    String name = "test";
//
//    @OneToMany(targetEntity = Ingredient.class)
//    Set<Ingredient> ingredients;
//
//}
