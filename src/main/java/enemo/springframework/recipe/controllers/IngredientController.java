package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.commands.IngredientCommand;
import enemo.springframework.recipe.services.IngredientService;
import enemo.springframework.recipe.services.RecipeService;
import enemo.springframework.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService service, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = service;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("recipe/ingredients/{recipeId}")
    public String listIngredients(@PathVariable String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(recipeId)));

        return "recipes/ingredient/list";


    }

    @GetMapping
    @RequestMapping("recipe/ingredient/{recipeId}/show/{ingredientId}")
    public String showIngredients(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        model.addAttribute("ingredient", ingredientCommand);


        return "recipes/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/ingredient/{recipeId}/update/{ingredientId}")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listOfUoms());


        return "recipes/ingredient/ingredientform";


    }


}
