package ba.unsa.etf.nwt.ingredient_service.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Getter
@Setter
public class IngredientRecipe {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column
    private Integer quantity;

    @Column(columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID recipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_recipeid_id", nullable = false)
    private Ingredient ingredientRecipeID;

}
