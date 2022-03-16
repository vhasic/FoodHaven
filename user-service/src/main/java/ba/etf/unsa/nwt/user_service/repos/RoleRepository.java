package ba.etf.unsa.nwt.user_service.repos;

import ba.etf.unsa.nwt.user_service.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
