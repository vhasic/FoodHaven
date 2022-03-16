package ba.unsa.etf.nwt.ingredient_service.repos;

import ba.unsa.etf.nwt.ingredient_service.domain.IngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Integer> {
}
