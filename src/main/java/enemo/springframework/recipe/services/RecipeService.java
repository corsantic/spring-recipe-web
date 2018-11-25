package enemo.springframework.recipe.services;

import enemo.springframework.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService  {

    Set<Recipe> getRecipes();

    Recipe getById(Long id);
}
