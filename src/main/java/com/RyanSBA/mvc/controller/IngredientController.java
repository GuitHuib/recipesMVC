package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.IngredientDto;
import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.RecipeIngredients;
import com.RyanSBA.mvc.service.IngredientServiceImpl;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Controller
public class IngredientController {

    @Autowired
    IngredientServiceImpl iRepo;
    @Autowired
    RecipeServiceImpl rRepo;

    @PostMapping("addingredients")
    public RedirectView addIngredients(@ModelAttribute IngredientDto dto, RedirectAttributes model) {
        // check if ingredient already exists, else save new
        Optional<Ingredient> _ingredient = Optional.ofNullable(iRepo.findByName(dto.getName()));
        Ingredient ingredient = _ingredient.isPresent() ? _ingredient.get() :  new Ingredient(dto.getName());
        if (!_ingredient.isPresent()) iRepo.saveIngredient(ingredient);

        //find recipe, add ingredient
        Recipe recipe = rRepo.findById(dto.getRecipe_id());
        RecipeIngredients recipeIngredients = new RecipeIngredients(recipe, ingredient, dto.getAmount(), dto.getMeasurement());
        rRepo.addIngredient(recipe, recipeIngredients);

        //forward recipe info
        model.addFlashAttribute("recipe", recipe);
        return new RedirectView("/edit/" + recipe.getId());
    }
}