package ba.etf.unsa.nwt.user_service.user_service.repos;

import ba.etf.unsa.nwt.user_service.user_service.domain.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
