package enemo.springframework.recipe.controllers;


import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.exceptions.NotFoundException;
import enemo.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model) {


        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));


        return "recipes/show";


    }


    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipeform";
    }


    @GetMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(id)));

        return "recipes/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);


        return "redirect:/recipe/show/" + savedCommand.getId();
    }


    @GetMapping("recipe/delete/{id}")
    public String deleteById(@PathVariable String id) {


        log.debug("Deleting By id : " + id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);


        return modelAndView;

    }


}
