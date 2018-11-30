package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.controllers.ImageController;
import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;


    ImageService imageService;





    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeRepository);



    }


    @Test
    public void saveImageFile() throws IOException {

        Long id = 1L;

        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Enemo Spring Framework".getBytes());

        Recipe recipe = new Recipe();

        recipe.setId(id);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //when
        imageService.saveImageFile(id, multipartFile);

        //then

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();


        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);


    }


}