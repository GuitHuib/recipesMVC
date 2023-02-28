package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeServiceTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    RecipeServiceImpl recipeService;

    static User testUser;
    static Recipe testRecipe;
    static Recipe otherRecipe;
    @BeforeAll
    static void setUp() {
        testUser = new User();
        testUser.setEmail("testUser@test.com");
        testUser.setPassword("Password123$");

        testRecipe = new Recipe();
        testRecipe.setName("test");
        testRecipe.setUser(testUser);

        otherRecipe = new Recipe();
        otherRecipe.setName("other");
        otherRecipe.setUser(testUser);
    }
    @Test
    @Order(1)
    public void createRecipeTest() {
        userService.createUser(testUser);

        recipeService.saveRecipe(testRecipe);
        int start = recipeService.findAllRecipes().size();
        recipeService.saveRecipe(otherRecipe);
        int ending = recipeService.findAllRecipes().size();
        Assertions.assertEquals(start + 1, ending);
    }
    @Test
    @Order(2)
    public void findByIdTest() {
        Recipe actual = recipeService.findById(testRecipe.getId());
        Assertions.assertEquals(testRecipe.getName(), actual.getName());
    }
    @Test
    @Order(3)
    public void updateUserTest() {
        testRecipe.setName("newName");
        recipeService.updateRecipe(testRecipe);
        Recipe actual = recipeService.findById(testRecipe.getId());
        Assertions.assertEquals(testRecipe.getName(), actual.getName());
    }
    @Test
    @Order(4)
    public void deleteRecipeTest() {
        int start = recipeService.findAllRecipes().size();
        Recipe recipe = recipeService.findById(testRecipe.getId());
        Recipe recipe2 = recipeService.findById(otherRecipe.getId());
        recipeService.deleteRecipe(recipe);
        recipeService.deleteRecipe(recipe2);
        int ending = recipeService.findAllRecipes().size();
        Assertions.assertEquals(start -2, ending);

        User user = userService.findByEmail(testUser.getEmail());
        userService.deleteUser(user);
    }

}
