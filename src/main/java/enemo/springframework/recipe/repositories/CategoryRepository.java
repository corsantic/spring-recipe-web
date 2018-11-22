package enemo.springframework.recipe.repositories;

import enemo.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {


}
