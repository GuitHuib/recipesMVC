package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredients;
import com.RyanSBA.mvc.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeIngredientServiceTest {
    @Autowired
    RecipeServiceImpl recipeService;
    @Autowired
    IngredientServiceImpl ingredientService;
    @Autowired
    RecipeIngredientsServiceImpl recipeIngredientsService;

    static Recipe recipe;
    static Ingredient ingredient;
    static RecipeIngredients recipeIngredients;

    @BeforeAll
    static void setUp() {
        recipe = new Recipe();
        recipe.setName("recipe");

        ingredient = new Ingredient();
        ingredient.setName("ingredient");
    }
    @Test
    @Order(1)
    public void addIngredientToRecipeTest() {
        recipeService.saveRecipe(recipe);
        ingredientService.saveIngredient(ingredient);

        recipeIngredients = new RecipeIngredients(recipe, ingredient);
        recipeIngredientsService.saveRecipeIngredient(recipeIngredients);

        Assertions.assertNotNull(recipeIngredientsService.findAllRecipesIngredients());
    }
    @Test
    @Order(2)
    public void deleteRecipeIngredientsTest() {
        int start = recipeIngredientsService.findAllRecipesIngredients().size();
        recipeIngredientsService.deleteRecipeIngredients(recipeIngredients);
        int ending = recipeIngredientsService.findAllRecipesIngredients().size();
        Assertions.assertEquals(start-1, ending);
    }

}
