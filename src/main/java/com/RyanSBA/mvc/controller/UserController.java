package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.IngredientDto;
import com.RyanSBA.mvc.DTO.RecipeDto;
import com.RyanSBA.mvc.DTO.UserDto;
import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.service.IngredientServiceImpl;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import com.RyanSBA.mvc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RecipeServiceImpl recipeService;

    @Autowired
    IngredientServiceImpl ingredientService;

    @GetMapping("/sign-up")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/sign-up")
    public String newUser(@ModelAttribute UserDto dto, HttpServletRequest req, Model model) {
        // check that password and confirmation match
        if (!dto.getPassword().equals(dto.getPasswordConfirmation())){
            model.addAttribute("user", new UserDto());
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }
        //create new user
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        // attempt to save new user
        try {
            userService.createUser(user);
            log.info("USER "+user.getId()+" CREATED");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("user", new UserDto());
            model.addAttribute("error", "Account for that email already exists");
            log.info("ERROR, "+user.getEmail()+" ALREADY EXISTS; "+e.getMessage());
            return "signup";
        }
        //automatically login after sign up
        try {
            req.login(dto.getEmail(), dto.getPassword());
        } catch(ServletException e) {
            log.error("Login Error ", e);
            return "login";
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/myRecipes")
    public String showUser(Model model, Principal principle) {
        User user = userService.findByEmail(principle.getName());
        model.addAttribute("user", user);
        return "myrecipes";
    }

    @GetMapping("/shoppingList")
    public String showList(Model model, Principal principle) {
        User user = userService.findByEmail(principle.getName());
        model.addAttribute("user", user);
        return "shoppinglist";
    }

    @PostMapping("/addToList")
    public RedirectView addToList(@ModelAttribute RecipeDto dto, Principal principle) {
        //locate recipe ingredients
        Recipe recipe = recipeService.findById(dto.getId());
        Set<Ingredient> ingredients = recipe.getIngredients();
        // add to user's shopping list
        User user = userService.findByEmail(principle.getName());
        user.addToShoppingList(ingredients);
        userService.updateUser(user);
        return new RedirectView("/recipe/" + recipe.getId());
    }

    @PostMapping("/deleteItem")
    public RedirectView deleteItem(@ModelAttribute Ingredient data, Principal principal){
        User user = userService.findByEmail(principal.getName());
        Ingredient ingredient = ingredientService.findById(data.getId()).get();
        user.removeItem(ingredient);
        userService.updateUser(user);
        return new RedirectView("/shoppingList");
    }

    @PostMapping("/addItem")
        public RedirectView addItem(@ModelAttribute IngredientDto dto, Principal principal) {
            Optional<Ingredient> _ingredient = ingredientService.findByName(dto.getName());
            Ingredient ingredient = _ingredient.orElse(new Ingredient(dto.getName().trim()));
            if (_ingredient.isEmpty()) ingredientService.saveIngredient(ingredient);

            Set<Ingredient> ingredients = new HashSet<>();
            ingredients.add(ingredient);

            User user = userService.findByEmail(principal.getName());
            user.addToShoppingList(ingredients);
            userService.updateUser(user);

            return new RedirectView("/shoppingList");
    }
}
