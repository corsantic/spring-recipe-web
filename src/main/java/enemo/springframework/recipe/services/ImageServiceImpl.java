package enemo.springframework.recipe.services;

import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.domain.Recipe;
import enemo.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {
        Recipe recipe = recipeRepository.findById(id).get();

        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;

            }
            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {

            log.error("Error Occured ", e);
            e.printStackTrace();
        }


    }



}
