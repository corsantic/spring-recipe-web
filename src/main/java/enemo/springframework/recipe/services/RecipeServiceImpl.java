package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.converters.RecipeCommandToRecipe;
import enemo.springframework.recipe.converters.RecipeToRecipeCommand;
import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("## I'm in the service ");
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe getById(Long id) {

        log.debug(" Getting Recipe by id : " + id);


        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new RuntimeException("Recipe Not Found");
        }


        return recipe.get();


    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {

        log.debug(" Saving Recipe with RecipeCommand ");

        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        log.debug("Saved RecipeId: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);


    }

    @Transactional
    @Override
    public RecipeCommand findByCommandId(Long id) {


        return recipeToRecipeCommand.convert(getById(id));


    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
