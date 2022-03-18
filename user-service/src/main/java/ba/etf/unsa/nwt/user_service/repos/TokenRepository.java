package ba.etf.unsa.nwt.user_service.repos;

import ba.etf.unsa.nwt.user_service.domain.Token;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TokenRepository extends JpaRepository<Token, UUID> {
}
