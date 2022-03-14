package etf.unsa.ba.nwt.recipe_service;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import etf.unsa.ba.nwt.recipe_service.domain.Step;
import etf.unsa.ba.nwt.recipe_service.repos.CategoryRepository;
import etf.unsa.ba.nwt.recipe_service.repos.PictureRepository;
import etf.unsa.ba.nwt.recipe_service.repos.RecipeRepository;
import etf.unsa.ba.nwt.recipe_service.repos.StepRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RecipeServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RecipeServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RecipeServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RecipeRepository recipeRepository, PictureRepository pictureRepository, CategoryRepository categoryRepository, StepRepository stepRepository) {
        return (args) -> {
            pictureRepository.save(new Picture("image1"));
            pictureRepository.save(new Picture("image2"));
            pictureRepository.save(new Picture("image3"));
            Picture p1 = pictureRepository.findById(1).get();
            Picture p2 = pictureRepository.findById(2).get();
            Picture p3 = pictureRepository.findById(3).get();
            categoryRepository.save(new Category("French",  p1));
            categoryRepository.save(new Category("Italian",  p2));
            categoryRepository.save(new Category("Mexican",  p3));
            Category c1 = categoryRepository.findById(1).get();
            Category c2 = categoryRepository.findById(2).get();
            Category c3 = categoryRepository.findById(3).get();
            recipeRepository.save(new Recipe("Creme brulee", "This Creme Brulee recipe is delicious, creamy, and the most perfect French dessert...", 30, 1,p1 ,c1));
            recipeRepository.save(new Recipe("Tajarin al Tartufo", "Tajarin is the Piemontese version of tagliatelle.", 20, 1,p2 ,c2));
            recipeRepository.save(new Recipe("Mexican Tortilla", "Tortillas are an all-time family favourite down Mexico way, and they're fast becoming just as popular around Kiwi tables. ", 40, 1,p3 ,c3));
            Recipe r1 = recipeRepository.findById(1).get();
            Recipe r2 = recipeRepository.findById(2).get();
            Recipe r3 = recipeRepository.findById(3).get();
            stepRepository.save(new Step("In saucepan, combine milk and whipping cream",p1,r1));
            stepRepository.save(new Step("Heat a non-stick frying pan or wok with a dash of oil, stir fry schnitzel strips until browned, this is best done in two batches, set aside.",p2,r2));
            stepRepository.save(new Step("Using a rolling pin, roll out the pieces of dough into thin, flat rectangles. Leave to rest for a few minutes.",p3,r3));
        };
    }
}
