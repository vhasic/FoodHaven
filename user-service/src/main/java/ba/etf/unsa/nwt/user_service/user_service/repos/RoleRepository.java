package ba.etf.unsa.nwt.user_service.user_service.repos;

import ba.etf.unsa.nwt.user_service.user_service.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    //    Mora postojati Rola pod nazivom User
    @Query(value = "SELECT * FROM role where name=:name", nativeQuery = true)
    Role findByName(String name);
}