package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Step;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StepRepository extends JpaRepository<Step, UUID> {
}
