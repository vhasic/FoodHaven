package etf.unsa.ba.nwt.recipe_service.repos;

import etf.unsa.ba.nwt.recipe_service.domain.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT * FROM Category c where c.name=:categoryName", nativeQuery = true)
    Optional<Category> getCategoryByName(@Param("categoryName") String categoryName);
}
