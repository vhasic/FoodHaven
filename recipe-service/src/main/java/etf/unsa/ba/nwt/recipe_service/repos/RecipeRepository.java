package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}
