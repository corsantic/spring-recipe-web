package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.UnitOfMeasureCommand;
import enemo.springframework.recipe.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listOfUoms();

}
