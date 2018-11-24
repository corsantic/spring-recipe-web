package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    RecipeController recipeController;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);


    }

    @Test
    public void recipeList() {

        String viewName = recipeController.recipeList(model);

        assertEquals("recipes/index", viewName);
        verify(recipeService, times(1)).getRecipes();

        verify(model, times(1)).addAttribute(eq("recipes"),anySet());


    }
}