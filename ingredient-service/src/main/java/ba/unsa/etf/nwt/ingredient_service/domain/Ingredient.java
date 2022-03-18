package ba.unsa.etf.nwt.ingredient_service.domain;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column
    private String name;

    @Column
    private Integer calorieCount;

    @Column
    private Integer vitamins;

    @Column
    private Integer carbohidrates;

    @Column
    private Integer fat;

    @Column
    private Integer proteins;

    @Column(length = 50)
    private String measuringUnit;

    @OneToOne
    @JoinColumn(name = "ingredient_picture_id")
    private Picture ingredientPicture;

    @OneToMany(mappedBy = "ingredientRecipeID")
    private Set<IngredientRecipe> ingredientRecipeIDIngredientRecipes;

}
