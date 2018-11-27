package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.domain.Ingredient;
import enemo.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService service;

    public IngredientController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("recipe/ingredients/{recipeId}")
    public String listIngredients(@PathVariable String recipeId,Model model)
    {

        model.addAttribute("recipe", service.findByCommandId(Long.valueOf(recipeId)));


        return "recipes/ingredient/list";





    }



}
