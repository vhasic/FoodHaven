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
//            try {
//                MultipartFile file1 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/sugar.jpg")));
//                MultipartFile file2 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/milk.jpg")));
//                MultipartFile file3 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/butter.jpg")));
//                MultipartFile file4 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/cheese.jpg")));
//                MultipartFile file5 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/chicken.jpg")));
//                MultipartFile file6 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/chocolate.jpg")));
//                MultipartFile file7 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/mushrooms.jpg")));
//                MultipartFile file8 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/oil.jpg")));
//                MultipartFile file9 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/strawberry.jpg")));
//                MultipartFile file10 = new MockMultipartFile("image.jpg", new FileInputStream(new File("ingredient-service/src/main/java/ba/unsa/etf/nwt/ingredient_service/image/tomato.jpg")));
//
//                UUID p1=pictureService.create(file1);
//                UUID p2=pictureService.create(file2);
//                UUID p3=pictureService.create(file3);
//                UUID p4=pictureService.create(file4);
//                UUID p5=pictureService.create(file5);
//                UUID p6=pictureService.create(file6);
//                UUID p7=pictureService.create(file7);
//                UUID p8=pictureService.create(file8);
//                UUID p9=pictureService.create(file9);
//                UUID p10=pictureService.create(file10);
//
//                UUID uuid = UUID.fromString("83f7b480-c905-4e24-9b0c-b3038fc4b706");
//
//                UUID r1 = ingredientService.create(new IngredientDTO("Sugar", 15,
//                        0, 4, 0, 0, "gram", p1));
//                UUID r2 = ingredientService.create(new IngredientDTO("Milk", 149,
//                        4, 12, 8, 8, "ml", p2));
//                UUID r3 = ingredientService.create(new IngredientDTO("Butter",
//                        102, 0, 0, 12, 0, "gram", p3));
//                UUID r4 = ingredientService.create(new IngredientDTO("Cheese", 120,
//                        4, 12, 3, 14, "gram", p4));
//                UUID r5 = ingredientService.create(new IngredientDTO("Chicken", 120,
//                        1, 0, 2, 26, "gram", p5));
//                UUID r6 = ingredientService.create(new IngredientDTO("Chocolate",
//                        216, 0, 25, 13, 2, "gram", p6));
//                UUID r7 = ingredientService.create(new IngredientDTO("Mushrooms", 22,
//                        3, 3, 0, 3, "gram", p7));
//                UUID r8 = ingredientService.create(new IngredientDTO("Oil", 120,
//                        0, 0, 14, 0, "ml", p8));
//                UUID r9 = ingredientService.create(new IngredientDTO("Strawberry",
//                        32, 8, 8, 0, 1, "gram", p9));
//                UUID r10 = ingredientService.create(new IngredientDTO("Tomato", 18,
//                        9, 4, 0, 1, "gram", p10));
//
//
//                ingredientRecipeService.create(new IngredientRecipeDTO(1, uuid, r8));
//                ingredientRecipeService.create(new IngredientRecipeDTO(2, uuid, r2));
//                ingredientRecipeService.create(new IngredientRecipeDTO(3, uuid, r3));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        };
    }
}
