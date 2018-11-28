package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.commands.IngredientCommand;
import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.commands.UnitOfMeasureCommand;
import enemo.springframework.recipe.converters.IngredientCommandToIngredient;
import enemo.springframework.recipe.services.IngredientService;
import enemo.springframework.recipe.services.RecipeService;
import enemo.springframework.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("recipe/ingredients/{recipeId}/show/{ingredientId}")
    public String showIngredients(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        model.addAttribute("ingredient", ingredientCommand);


        return "recipes/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/ingredients/{recipeId}/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findByCommandId(Long.valueOf(recipeId));

        if(recipeCommand.getId() == null)
            log.debug("recipe Command id is NULL");

        // need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        // init uom
        model.addAttribute("uomList", unitOfMeasureService.listOfUoms());

        return "recipes/ingredient/ingredientform";


    }


    @GetMapping
    @RequestMapping("recipe/ingredients/{recipeId}/update/{ingredientId}")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listOfUoms());


        return "recipes/ingredient/ingredientform";


    }

    @PostMapping("recipe/ingredients/{recipeId}")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("saved recipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/ingredients/" + savedCommand.getRecipeId() + "/show/" + savedCommand.getId();

    }


}
