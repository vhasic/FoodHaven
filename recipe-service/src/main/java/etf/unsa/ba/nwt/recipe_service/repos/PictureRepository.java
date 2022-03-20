package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PictureRepository extends JpaRepository<Picture, UUID> {
}
