package ba.unsa.etf.nwt.ingredient_service.repos;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
