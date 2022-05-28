package ba.unsa.etf.nwt.ingredient_service.repos;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    @Query(value = "SELECT SUM(i.calorie_count*ir.quantity) FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    Integer getTotalCalories(@Param("RecipeID") String RecipeID);
    @Query(value = "SELECT SUM(i.vitamins*ir.quantity) FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    Integer getTotalVitamins(@Param("RecipeID") String RecipeID);
    @Query(value = "SELECT SUM(i.fat*ir.quantity) FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    Integer getTotalFat(@Param("RecipeID") String RecipeID);
    @Query(value = "SELECT SUM(i.proteins*ir.quantity) FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    Integer getTotalProteins(@Param("RecipeID") String RecipeID);
    @Query(value = "SELECT i.name, i.measuring_unit, ir.quantity FROM ingredient i, ingredient_recipe ir" +
            " WHERE i.id = ir.ingredient_recipeid_id AND ir.recipeid =:RecipeID", nativeQuery = true)
    List<Object> getIngredientInfo(@Param("RecipeID") String RecipeID);
}
