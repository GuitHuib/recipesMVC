package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.IngredientDto;
import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.service.IngredientServiceImpl;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@Controller
public class IngredientController {

    @Autowired
    IngredientServiceImpl iRepo;
    @Autowired
    RecipeServiceImpl rRepo;

//    @Autowired
//    RecipeIngredientsServiceImpl connService;

    @PostMapping("addingredients")
    public RedirectView addIngredients(@ModelAttribute IngredientDto dto, RedirectAttributes model) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setMeasurement(dto.getMeasurement());
        ingredient.setAmount(dto.getAmount());
        iRepo.saveIngredient(ingredient);


        iRepo.saveIngredient(ingredient);
        Recipe recipe = rRepo.findById(dto.getRecipe_id());
        Set<Ingredient> ingredients = recipe.getIngredients();
        ingredients.add(ingredient);
        rRepo.saveRecipe(recipe);

        //        Ingredient ingredient;
//        if (iRepo.findByName(dto.getName()) != null) {
//            ingredient = iRepo.findByName(dto.getName());
//        } else {
//            ingredient = new Ingredient();
//            ingredient.setName(dto.getName());
//            iRepo.saveIngredient(ingredient);
//        }
//
//        RecipeIngredients relation = new RecipeIngredients();
//        RecipeIngredientKey key = new RecipeIngredientKey();
//        key.setIngredientId(ingredient.getId());
//        key.setRecipeId(dto.getRecipe_id());
//        relation.setId(key);
//        relation.setAmount(dto.getAmount());
//        relation.setType(dto.getMeasurement());
//        connService.saveRecipeIngredient(relation);

//        Recipe recipe = rRepo.findById(dto.getRecipe_id());
        model.addFlashAttribute("recipe", recipe);


        return new RedirectView("/edit/" + recipe.getId());
    }
}
