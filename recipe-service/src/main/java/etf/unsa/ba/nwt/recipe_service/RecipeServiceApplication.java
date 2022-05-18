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
            try {

                stepService.deleteAll();
                recipeService.deleteAll();
                categoryService.deleteAll();
                pictureService.deleteAll();

                File file1 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/french.jpg");
                FileInputStream fis1 = new FileInputStream(file1);
                MockMultipartFile multipart1 = new MockMultipartFile("file", file1.getName(), "image/jpeg", fis1);

                File file2 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/italian.jpg");
                FileInputStream fis2 = new FileInputStream(file2);
                MockMultipartFile multipart2 = new MockMultipartFile("file", file2.getName(), "image/jpeg", fis2);

                File file3 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/mexican.jpg");
                FileInputStream fis3 = new FileInputStream(file3);
                MockMultipartFile multipart3 = new MockMultipartFile("file", file3.getName(), "image/jpeg", fis3);

                UUID pictureId1=pictureService.create(multipart1);
                UUID pictureId2=pictureService.create(multipart2);
                UUID pictureId3=pictureService.create(multipart3);

                File file4 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/cremeBrulee.jpg");
                FileInputStream fis4 = new FileInputStream(file4);
                MockMultipartFile multipart4 = new MockMultipartFile("file", file4.getName(), "image/jpeg", fis4);

                File file5 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/pasta.jpg");
                FileInputStream fis5 = new FileInputStream(file5);
                MockMultipartFile multipart5 = new MockMultipartFile("file", file5.getName(), "image/jpeg", fis5);

                File file6 = new File("recipe-service/src/main/java/etf/unsa/ba/nwt/recipe_service/image/tortilla.jpg");
                FileInputStream fis6 = new FileInputStream(file6);
                MockMultipartFile multipart6 = new MockMultipartFile("file", file6.getName(), "image/jpeg", fis6);

                UUID pictureId4=pictureService.create(multipart4);
                UUID pictureId5=pictureService.create(multipart5);
                UUID pictureId6=pictureService.create(multipart6);

                UUID categoryId1 =categoryService.create(new CategoryDTO("French", pictureId1));
                UUID categoryId2 =categoryService.create(new CategoryDTO("Italian", pictureId2));
                UUID categoryId3 =categoryService.create(new CategoryDTO("Mexican", pictureId3));

                UUID recipeId1 = recipeService.create(new RecipeDTO("Creme brulee", "This Creme Brulee recipe is delicious, creamy, and the most perfect French dessert...", 30,
                        UUID.fromString("3f343aac-db8f-4d77-9268-f2a109e3f36a"), pictureId4, categoryId1));
                UUID recipeId2 = recipeService.create(new RecipeDTO("Tajarin al Tartufo", "Tajarin is the Piemontese version of tagliatelle.", 20,
                        UUID.fromString("3f343aac-db8f-4d77-9268-f2a109e3f36a"),pictureId5, categoryId2));
                UUID recipeId3 = recipeService.create(new RecipeDTO("Mexican Tortilla", "Tortillas are an all-time family favourite down Mexico way, and they're fast becoming just as popular around Kiwi tables. ", 40,
                        UUID.fromString("3f343aac-db8f-4d77-9268-f2a109e3f36a"), pictureId6,categoryId3));

                stepService.create(new StepDTO("In saucepan, combine milk and whipping cream",1, pictureId4, recipeId1));
                stepService.create(new StepDTO("Heat a non-stick frying pan or wok with a dash of oil, stir fry schnitzel strips until browned, this is best done in two batches, set aside.",1, pictureId5,recipeId2));
                stepService.create(new StepDTO("Using a rolling pin, roll out the pieces of dough into thin, flat rectangles. Leave to rest for a few minutes.",1, pictureId6,recipeId3));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}