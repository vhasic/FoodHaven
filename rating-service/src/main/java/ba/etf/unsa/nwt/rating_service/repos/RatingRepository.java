package ba.etf.unsa.nwt.rating_service.repos;

import ba.etf.unsa.nwt.rating_service.domain.Rating;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
