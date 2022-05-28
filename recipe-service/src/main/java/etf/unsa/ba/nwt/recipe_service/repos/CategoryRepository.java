package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT * FROM Category c where c.name=:categoryName", nativeQuery = true)
    Optional<Category> getCategoryByName(@Param("categoryName") String categoryName);
}
