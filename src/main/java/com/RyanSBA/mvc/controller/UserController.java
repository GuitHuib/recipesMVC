package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.RecipeDto;
import com.RyanSBA.mvc.DTO.UserDto;
import com.RyanSBA.mvc.model.Ingredient;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import com.RyanSBA.mvc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Set;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl service;

    @Autowired
    RecipeServiceImpl recService;

    @GetMapping("/sign-up")
    public String newUser() {
        return "signup";
    }

    @PostMapping("/sign-up")
    public RedirectView newUser(@ModelAttribute UserDto dto, HttpServletRequest req) {
        service.createUser(dto);
        //automatically login after sign up
        try {
            req.login(dto.getEmail(), dto.getPassword());
        } catch(ServletException e) {
            log.error("Login Error ", e);
        }
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/myrecipes")
    public String showUser(Model model, Principal principle) {
        User user = service.findByEmail(principle.getName());
        model.addAttribute("user", user);
        return "myrecipes";
    }

    @GetMapping("/shoppinglist")
    public String showList(Model model, Principal principle) {
        User user = service.findByEmail(principle.getName());
        model.addAttribute("user", user);
        return "shoppinglist";
    }

    @PostMapping("/addToList")
    public RedirectView addToList(@ModelAttribute RecipeDto dto, Principal principle) {
        //locate recipe ingredients
        Recipe recipe = recService.findById(dto.getId());
        Set<Ingredient> ingredients = recipe.getIngredients();
        // add to user's shopping list
        User user = service.findByEmail(principle.getName());
        user.addToShoppingList(ingredients);
        service.updateUser(user);
        return new RedirectView("/recipe/" + recipe.getId());
    }

}
