package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes")
    public String recipeList(Model model) {

        log.debug("Recipes Page");

        model.addAttribute("recipes", recipeService.getRecipes());


        return "recipes/index";


    }


}
