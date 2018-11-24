package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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

        //given
        Set<Recipe> recipes = new HashSet<>();

        recipes.add(new Recipe());

        Recipe recipe = new Recipe();

        recipe.setDescription("new");
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);


        //when
        String viewName = recipeController.recipeList(model);

        //then
        assertEquals("recipes/index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());


        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }
}