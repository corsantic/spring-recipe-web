package enemo.springframework.recipe.controllers;

import enemo.springframework.recipe.commands.RecipeCommand;
import enemo.springframework.recipe.services.ImageService;
import enemo.springframework.recipe.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }


    @GetMapping("recipe/image/{id}")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(id)));

        return "recipes/imageuploadform";

    }

    @PostMapping("recipe/image/{id}")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/show/" + id;


    }

    @GetMapping("recipe/recipeimage/{id}")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findByCommandId(Long.valueOf(id));

        byte[] byteArray = new byte[recipeCommand.getImage().length];

        int i = 0;

        for (Byte wrappedByte : recipeCommand.getImage()) {
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());


    }

}
