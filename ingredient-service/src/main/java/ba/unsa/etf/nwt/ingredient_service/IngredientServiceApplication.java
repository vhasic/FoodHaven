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
            /*

            UUID uuid= UUID.randomUUID();
            UUID p1 = pictureService.create(new PictureDTO("image1"));
            UUID p2 = pictureService.create(new PictureDTO("image2"));
            UUID p3 = pictureService.create(new PictureDTO("image3"));
            UUID r1 = ingredientService.create(new IngredientDTO("Chicken", 200,
                    0, 12, 3, 12, "gram", p1));
            UUID r2 = ingredientService.create(new IngredientDTO("Milk", 200,
                    4, 0, 3, 5, "liter", p2));
            UUID r3 = ingredientService.create(new IngredientDTO("Strawberry",
                    200, 10, 2, 0, 4, "gram", p3));
            ingredientRecipeService.create(new IngredientRecipeDTO(1, uuid, r1));
            ingredientRecipeService.create(new IngredientRecipeDTO(2, uuid, r2));
            ingredientRecipeService.create(new IngredientRecipeDTO(3, uuid, r3));

             */

        };
    }
}
