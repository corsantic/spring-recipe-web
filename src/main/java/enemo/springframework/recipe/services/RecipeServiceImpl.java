package enemo.springframework.recipe.services;

import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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

       Optional<Recipe> recipe = recipeRepository.findById(id);

       if(!recipe.isPresent()){
           throw new RuntimeException("Recipe Not Found");
       }



       return recipe.get();



    }
}
