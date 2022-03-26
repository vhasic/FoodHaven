package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Recipe;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    @Query(value = "SELECT * FROM recipeservicedb.recipe r where r.recipe_category_id=:recipeCategory", nativeQuery = true)
    List<Recipe> getRecipesFromCategory(@Param("recipeCategory") String recipeCategory);
    @Query(value = "SELECT * FROM recipeservicedb.recipe r where r.userid=:recipeUser", nativeQuery = true)
    List<Recipe> getRecipesFromUser(@Param("recipeUser") String recipeUser);
}
