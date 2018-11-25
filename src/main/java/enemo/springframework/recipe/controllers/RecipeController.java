package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/show/{id}")
    public String getRecipeById(@PathVariable String id ,Model model){

        model.addAttribute("recipe",recipeService.getById(Long.valueOf(id)));


        return "recipes/show";


    }

}
