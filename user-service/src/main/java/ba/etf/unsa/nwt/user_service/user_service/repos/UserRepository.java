package ba.etf.unsa.nwt.user_service.user_service.repos;

import ba.etf.unsa.nwt.user_service.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT * FROM User u, Role r WHERE r.id = u.role_id AND r.name =:roleName", nativeQuery = true)
    List<User> getUsersByRole(@Param("roleName") String roleName);
    User getUserByUsername(String username);
}
