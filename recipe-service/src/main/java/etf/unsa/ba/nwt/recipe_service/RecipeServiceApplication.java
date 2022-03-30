package etf.unsa.ba.nwt.recipe_service;

import etf.unsa.ba.nwt.recipe_service.model.CategoryDTO;
import etf.unsa.ba.nwt.recipe_service.model.PictureDTO;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.model.StepDTO;
import etf.unsa.ba.nwt.recipe_service.service.CategoryService;
import etf.unsa.ba.nwt.recipe_service.service.PictureService;
import etf.unsa.ba.nwt.recipe_service.service.RecipeService;
import etf.unsa.ba.nwt.recipe_service.service.StepService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class RecipeServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(RecipeServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RecipeServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(RecipeService recipeService, PictureService pictureService, CategoryService categoryService, StepService stepService) {
        return (args) -> {
           /* MultipartFile file = null;
            try {
                file = new MockMultipartFile("image.jpg", new FileInputStream(new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/image.jpg")));
                UUID pictureId1=pictureService.create(file);
                UUID pictureId2=pictureService.create(file);
                UUID pictureId3=pictureService.create(file);


                UUID categoryId1 =categoryService.create(new CategoryDTO("French", pictureId1));
                UUID categoryId2 =categoryService.create(new CategoryDTO("Italian", pictureId2));
                UUID categoryId3 =categoryService.create(new CategoryDTO("Mexican", pictureId3));

                UUID recipeId1 = recipeService.create(new RecipeDTO("Creme brulee", "This Creme Brulee recipe is delicious, creamy, and the most perfect French dessert...", 30, UUID.randomUUID(),pictureId1 ,categoryId1));
                UUID recipeId2 = recipeService.create(new RecipeDTO("Tajarin al Tartufo", "Tajarin is the Piemontese version of tagliatelle.", 20, UUID.randomUUID(),pictureId2, categoryId2));
                UUID recipeId3 = recipeService.create(new RecipeDTO("Mexican Tortilla", "Tortillas are an all-time family favourite down Mexico way, and they're fast becoming just as popular around Kiwi tables. ", 40, UUID.randomUUID(), pictureId3,categoryId3));

                stepService.create(new StepDTO("In saucepan, combine milk and whipping cream",1, pictureId1, recipeId1));
                stepService.create(new StepDTO("Heat a non-stick frying pan or wok with a dash of oil, stir fry schnitzel strips until browned, this is best done in two batches, set aside.",1, pictureId2,recipeId2));
                stepService.create(new StepDTO("Using a rolling pin, roll out the pieces of dough into thin, flat rectangles. Leave to rest for a few minutes.",1, pictureId3,recipeId3));
            } catch (IOException e) {
                e.printStackTrace();
            }
        */};
    }
}