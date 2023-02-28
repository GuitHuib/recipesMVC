package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.Ingredient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IngredientServiceTest {

    static Ingredient testIngredient;
    static Ingredient otherIngredient;

    @Autowired
    IngredientServiceImpl ingredientService;

    @BeforeAll
    static public void setUp() {
        testIngredient = new Ingredient();
        testIngredient.setName("test");

        otherIngredient = new Ingredient();
        otherIngredient.setName("other");
    }

    @Test
    @Order(1)
    public void saveIngredientTest(){
        ingredientService.saveIngredient(testIngredient);
        int start = ingredientService.findAllIngredients().size();

        ingredientService.saveIngredient(otherIngredient);
        int ending = ingredientService.findAllIngredients().size();

        Assertions.assertEquals(start + 1, ending);
    }
    @Test
    @Order(2)
    public void findByIdTest() {
        Ingredient actual = ingredientService.findById(testIngredient.getId()).get();
        Assertions.assertEquals(testIngredient.getName(), actual.getName());
    }
    @Test
    @Order(3)
    public void findByNameTest() {
        Ingredient actual = ingredientService.findByName(testIngredient.getName()).get();
        Assertions.assertEquals(testIngredient.getName(), actual.getName());
    }
    @Test
    @Order(4)
    public void updateIngredientTest() {
        testIngredient.setName("newName");
        ingredientService.saveIngredient(testIngredient);
        Ingredient actual = ingredientService.findByName(testIngredient.getName()).get();
        Assertions.assertEquals(testIngredient.getName(), actual.getName());
    }
    @Test
    @Order(5)
    public void deleteIngredientTest() {
        int start = ingredientService.findAllIngredients().size();
        Ingredient first = ingredientService.findByName(testIngredient.getName()).get();
        Ingredient second = ingredientService.findByName(otherIngredient.getName()).get();
        ingredientService.deleteIngredient(first);
        ingredientService.deleteIngredient(second);
        int ending = ingredientService.findAllIngredients().size();
        Assertions.assertEquals(start-2, ending);
    }
}
