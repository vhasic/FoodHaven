package ba.etf.unsa.nwt.user_service.repos;

import ba.etf.unsa.nwt.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
