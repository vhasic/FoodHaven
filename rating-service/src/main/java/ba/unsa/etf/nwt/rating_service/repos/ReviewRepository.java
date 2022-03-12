package ba.unsa.etf.nwt.rating_service.repos;

import ba.unsa.etf.nwt.rating_service.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
