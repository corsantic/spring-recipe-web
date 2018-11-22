package enemo.springframework.recipe.repositories;

import enemo.springframework.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {


}
