package ba.unsa.etf.nwt.ingredient_service;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.model.IngredientRecipeDTO;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRecipeRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.PictureRepository;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientRecipeService;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientService;
import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class IngredientServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(IngredientServiceApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(IngredientServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PictureService pictureService, IngredientService ingredientService,
                                  IngredientRecipeService ingredientRecipeService) {
        return (args) -> {

            MultipartFile file = null;
            try {
                file = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
                UUID p1=pictureService.create(file);
                UUID p2=pictureService.create(file);
                UUID p3=pictureService.create(file);
                UUID uuid = UUID.fromString("0142b320-c67f-4d29-a58b-38a5cf6ec632");
                UUID r1 = ingredientService.create(new IngredientDTO("Chicken", 200,
                        0, 12, 3, 12, "gram", p1));
                UUID r2 = ingredientService.create(new IngredientDTO("Milk", 200,
                        4, 0, 3, 5, "liter", p2));
                UUID r3 = ingredientService.create(new IngredientDTO("Strawberry",
                        200, 10, 2, 0, 4, "gram", p3));
                ingredientRecipeService.create(new IngredientRecipeDTO(1, uuid, r1));
                ingredientRecipeService.create(new IngredientRecipeDTO(2, uuid, r2));
                ingredientRecipeService.create(new IngredientRecipeDTO(3, uuid, r3));
            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    }
}
