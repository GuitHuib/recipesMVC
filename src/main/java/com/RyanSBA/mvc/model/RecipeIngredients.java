//package com.RyanSBA.mvc.model;
//
//import jakarta.persistence.EmbeddedId;
//import jakarta.persistence.Entity;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class RecipeIngredients {
//
//    @EmbeddedId
//    RecipeIngredientKey id;
//
//    @ManyToOne
//    @MapsId("recipeId")
//    Recipe recipe;
//
//    @ManyToOne
//    @MapsId("ingredientId")
//    Ingredient ingredient;
//
//    private double amount;
//
//    private String type;
//}
