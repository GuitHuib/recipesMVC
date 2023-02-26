package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.RecipeDto;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import com.RyanSBA.mvc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {

    @Autowired
    RecipeServiceImpl recipeService;
    @Autowired
    UserServiceImpl userService;

    // view index of all recipes
    @GetMapping("/")
    public String showRecipes(Model model){
        List<Recipe> recipes = recipeService.findAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipesIndex";
    }

    // view individual recipe
    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable int id, Model model) {
        Recipe recipe = recipeService.findById(id);
        User user = recipe.getUser();
        model.addAttribute("recipe", recipe);
        model.addAttribute("user", user);
        return "recipe";
    }

    // navigate to edit  page
    @GetMapping("/edit/{id}")
    public String editRecipe(@PathVariable int id, Model model, Principal principal) {
        Recipe recipe = recipeService.findById(id);
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("recipe", recipe);
        if (user.getEmail() == recipe.getUser().getEmail()){
            return "/editrecipe";
        }
        else {
            return "recipe";
        }
    }

    // create new recipe form
    @GetMapping("/createrecipe")
    public String createRecipe(Model model, Principal principle){
        String email = principle.getName();
        model.addAttribute("email", email);
        return "newrecipe";
    }

    // save new recipe, redirect to edit page for further details
    @PostMapping("/createrecipe")
    public RedirectView createRecipe(@ModelAttribute RecipeDto dto, RedirectAttributes model){
        Recipe recipe = new Recipe();
        User user = userService.findByEmail(dto.getEmail());

        // convert dto to ingredient model
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setUser(user);
        recipe.setVegetarian(dto.isVegetarian());
        recipe.setVegan(dto.isVegan());
        recipe.setGlutenFree(dto.isGlutenFree());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServes(dto.getServes());
        recipeService.saveRecipe(recipe);

        user.addRecipe(recipe);
        userService.updateUser(user);

        model.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

    // edit recipe details, non ingredients/instructions
    @PostMapping("/editdetails")
    public RedirectView editDetails(@ModelAttribute RecipeDto dto, RedirectAttributes att) {
        Recipe recipe = recipeService.findById(dto.getId());
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setVegetarian(dto.isVegetarian());
        recipe.setVegan(dto.isVegan());
        recipe.setGlutenFree(dto.isGlutenFree());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServes(dto.getServes());
        recipeService.saveRecipe(recipe);

        att.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

    // edit recipe instructions
    @PostMapping("/editinstructions")
    public RedirectView editDirections(@ModelAttribute RecipeDto data, RedirectAttributes att) {
        Recipe recipe = recipeService.findById(data.getId());
        recipe.setInstructions(data.getInstructions());
        recipeService.saveRecipe(recipe);

        att.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

}
