package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PictureRepository extends JpaRepository<Picture, UUID> {
}
