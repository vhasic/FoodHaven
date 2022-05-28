package ba.unsa.etf.nwt.system_events_service.repos;

import ba.unsa.etf.nwt.system_events_service.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ActionRepository extends JpaRepository<Action, UUID> {
}
