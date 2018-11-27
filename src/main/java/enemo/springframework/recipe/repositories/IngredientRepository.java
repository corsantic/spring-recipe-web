package enemo.springframework.recipe.repositories;

import enemo.springframework.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository  extends CrudRepository<Ingredient,Long> {
}
