package ba.unsa.etf.nwt.ingredient_service;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import ba.unsa.etf.nwt.ingredient_service.domain.IngredientRecipe;
import ba.unsa.etf.nwt.ingredient_service.domain.Picture;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRecipeRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.PictureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IngredientServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(IngredientServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IngredientServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IngredientRepository ingredientRepository, PictureRepository pictureRepository, IngredientRecipeRepository ingredientRecipeRepository) {
        return (args) -> {
            pictureRepository.save(new Picture("image1"));
            pictureRepository.save(new Picture("image2"));
            pictureRepository.save(new Picture("image3"));
            Picture p1 = pictureRepository.findById(1).get();
            Picture p2 = pictureRepository.findById(2).get();
            Picture p3 = pictureRepository.findById(3).get();
            ingredientRepository.save(new Ingredient("Chicken", 200,
                    0, 12, 3, 12, "gram", p1));
            ingredientRepository.save(new Ingredient("Milk", 200,
                    4, 0, 3, 5, "liter", p2));
            ingredientRepository.save(new Ingredient("Strawberry",
                    200, 10, 2, 0, 4, "gram", p3));
            Ingredient r1 = ingredientRepository.findById(1).get();
            Ingredient r2 = ingredientRepository.findById(2).get();
            Ingredient r3 = ingredientRepository.findById(3).get();
            ingredientRecipeRepository.save(new IngredientRecipe(1, 1, r1));
            ingredientRecipeRepository.save(new IngredientRecipe(2,  2, r2));
            ingredientRecipeRepository.save(new IngredientRecipe(3,  2, r3));
            IngredientRecipe c1 = ingredientRecipeRepository.findById(1).get();
            IngredientRecipe c2 = ingredientRecipeRepository.findById(2).get();
            IngredientRecipe c3 = ingredientRecipeRepository.findById(3).get();
        };
    }
}
