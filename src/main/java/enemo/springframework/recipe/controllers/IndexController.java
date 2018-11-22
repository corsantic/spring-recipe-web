package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.domain.Category;
import enemo.springframework.recipe.domain.UnitOfMeasure;
import enemo.springframework.recipe.repositories.CategoryRepository;
import enemo.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        System.out.println("Cat Id is : "+ categoryOptional.get().getId());
        System.out.println("Uom Id is : "+ unitOfMeasureOptional.get().getId());

        return "index";
    }

}
