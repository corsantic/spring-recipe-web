package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService  {

    Set<Recipe> getRecipes();

    Recipe getById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findByCommandId(Long aLong);

    void deleteById(Long id);
}
