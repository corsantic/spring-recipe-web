package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);


    }

    @Test
    public void testMockMVC() throws   Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/index"));

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
        String viewName = indexController.recipeList(model);

        //then
        assertEquals("recipes/index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());


        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }
}