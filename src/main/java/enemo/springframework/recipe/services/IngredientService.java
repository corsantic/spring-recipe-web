package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.IngredientCommand;
import enemo.springframework.recipe.domain.Ingredient;

public interface IngredientService  {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long recipeId, Long idToDelete);
}
