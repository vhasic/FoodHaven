package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import etf.unsa.ba.nwt.recipe_service.domain.Step;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StepRepository extends JpaRepository<Step, UUID> {
    @Query(value = "SELECT * FROM Step s where s.step_recipe_id=:stepRecipe order by s.o_number", nativeQuery = true)
    List<Step> getStepsForRecipe(@Param("stepRecipe") String stepRecipe);
    @Query(value = "SELECT * FROM Step s where s.step_recipe_id=:stepRecipe and s.o_number=:oNumber", nativeQuery = true)
    Optional<Step> getStepXForRecipe(@Param("stepRecipe") String stepRecipe, @Param("oNumber") Integer oNumber);
}
