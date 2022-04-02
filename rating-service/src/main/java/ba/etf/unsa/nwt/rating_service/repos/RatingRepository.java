package ba.etf.unsa.nwt.rating_service.repos;

import ba.etf.unsa.nwt.rating_service.domain.Rating;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query(value = "SELECT avg(rating) FROM Rating r where r.recipe_id=:recipeId", nativeQuery = true)
    Double getAverageRatingByRecipeId(String recipeId); // ovdje nije radio parametar UUID, već je morao biti String

    Integer countByRecipeId(UUID recipeId);

    List<Rating> getAllByUserId(UUID userId);

    List<Rating> getAllByRecipeId(UUID recipeId);
}
