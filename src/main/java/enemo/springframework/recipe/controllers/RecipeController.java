package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));


        return "recipes/show";


    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(id)));

        return "recipes/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);


        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/delete/{id}")
    public String deleteById(@PathVariable String id) {


        log.debug("Deleting By id : "+ id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";

    }


}
