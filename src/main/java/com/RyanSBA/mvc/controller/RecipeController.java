package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.InstructionDto;
import com.RyanSBA.mvc.DTO.RecipeDto;
import com.RyanSBA.mvc.model.InstructionStep;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.service.InstructionStepServiceImpl;
import com.RyanSBA.mvc.service.RecipeServiceImpl;
import com.RyanSBA.mvc.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    RecipeServiceImpl recipeService;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    InstructionStepServiceImpl instService;

    // view index of all recipes
    @GetMapping("/")
    public String showRecipes(Model model){
        List<Recipe> recipes = recipeService.findAllRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
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
        return user.getEmail().equals(recipe.getUser().getEmail()) ? "/editrecipe" : "recipe";
    }

    // display new recipe form
    @GetMapping("/createRecipe")
    public String createRecipe(Model model, Principal principle){
        String email = principle.getName();
        model.addAttribute("email", email);
        return "newrecipe";
    }

    // save new recipe, redirect to edit page for further details
    @PostMapping("/createRecipe")
    public RedirectView createRecipe(@ModelAttribute RecipeDto dto,
                                     RedirectAttributes model,
                                     @RequestParam("image")MultipartFile multipartFile)
        throws IOException
    {
        Recipe recipe = new Recipe();
        User user = userService.findByEmail(dto.getEmail());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // convert dto to ingredient model
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setImage(fileName);
        recipe.setUser(user);
        recipe.setVegetarian(dto.isVegetarian());
        recipe.setVegan(dto.isVegan());
        recipe.setGlutenFree(dto.isGlutenFree());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServes(dto.getServes());
        recipeService.saveRecipe(recipe);
        log.info("RECIPE "+recipe.getId()+" CREATED");

        user.addRecipe(recipe);
        userService.updateUser(user);

        // save image in directory
        // * move to helper class *
        String uploadDir = "src/main/resources/static/images/upload/" + recipe.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("ImageSaved");
        } catch (IOException e) {
            throw new IOException("Could not save image");
        }

        model.addFlashAttribute("recipe", recipe);
        return new RedirectView("/edit/" + recipe.getId());
    }

    // edit recipe details, non ingredients/instructions
    @PostMapping("/editDetails")
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
        log.info("RECIPE "+recipe.getId()+" UPDATED");

        att.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

    // edit recipe instructions
    @PostMapping(value = "/editInstructions", params = "update")
    public RedirectView editDirections(@ModelAttribute InstructionDto data, RedirectAttributes att) {
        Recipe recipe = recipeService.findById(data.getRecipe_id());
        InstructionStep step;
        Optional<InstructionStep> _step = instService.findById(data.getId());
        step = _step.orElseGet(InstructionStep::new);
        step.setText(data.getText());
        instService.saveInstructionStep(step);

        if (_step.isEmpty()) {
            recipe.addInstruction(step);
            recipeService.saveRecipe(recipe);
        }
        log.info("RECIPE "+recipe.getId()+" UPDATED");
        att.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

    // add/edit recipe instructions
    @PostMapping(value = "/editInstructions", params = "delete")
    public RedirectView deleteInstruction(@ModelAttribute InstructionDto data, RedirectAttributes att) {
        Recipe recipe = recipeService.findById(data.getRecipe_id());
        InstructionStep step = instService.findById(data.getId()).get();
        recipe.removeInstruction(step);
        instService.deleteInstructionStep(step);
        recipeService.updateRecipe(recipe);
        log.info("RECIPE "+recipe.getId()+" UPDATED");

        att.addFlashAttribute("recipe", recipe);

        return new RedirectView("/edit/" + recipe.getId());
    }

    //delete existing recipe
    @PostMapping("/deleteRecipe")
    public RedirectView deleteRecipe(@ModelAttribute RecipeDto dto, Principal principal) {
        Recipe recipe = recipeService.findById(dto.getId());
        User user = userService.findByEmail(principal.getName());
        user.removeRecipe(recipe);
        recipeService.deleteRecipe(recipe);
        log.info("RECIPE "+recipe.getId()+" DELETED");
        return new RedirectView("/");
    }

}
