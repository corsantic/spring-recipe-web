package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.commands.IngredientCommand;
import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.commands.UnitOfMeasureCommand;
import enemo.springframework.recipe.services.IngredientService;
import enemo.springframework.recipe.services.RecipeService;
import enemo.springframework.recipe.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {


    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;


    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    public void listIngredients() throws Exception {

        //given
        RecipeCommand recipeCommand = new RecipeCommand();

        when(recipeService.findByCommandId(anyLong())).thenReturn(recipeCommand);


        //when
        mockMvc.perform(get("/recipe/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));


        //then
        verify(recipeService,times(1)).findByCommandId(anyLong());


    }
    @Test
    public void showIngredient() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/ingredient/1/show/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
    @Test
    public void testUpdateIngredientForm() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listOfUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/ingredient/1/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }




}