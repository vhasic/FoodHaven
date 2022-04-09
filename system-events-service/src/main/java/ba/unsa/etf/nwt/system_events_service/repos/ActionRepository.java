package ba.unsa.etf.nwt.system_events_service.repos;

import ba.unsa.etf.nwt.system_events_service.domain.Action;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActionRepository extends JpaRepository<Action, UUID> {
}
