package ba.unsa.etf.nwt.ingredient_service.repos;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import ba.unsa.etf.nwt.ingredient_service.domain.IngredientRecipe;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    @Query(value = "SELECT SUM(i.calorie_count*ir.quantity) FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    Integer getTotalCalories(@Param("RecipeID") String RecipeID);
}
