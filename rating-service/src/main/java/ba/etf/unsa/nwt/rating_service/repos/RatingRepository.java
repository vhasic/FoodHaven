package ba.etf.unsa.nwt.rating_service.repos;

import ba.etf.unsa.nwt.rating_service.domain.Rating;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query(value = "SELECT avg(r.rating) FROM reviewservicedb.review r where recipe_id=?1", nativeQuery = true)
    Double getAverageRatingByRecipeId(UUID recipeId);

    List<Rating> getAllByUserId(UUID userId);

    List<Rating> getAllByRecipeId(UUID recipeId);
}
