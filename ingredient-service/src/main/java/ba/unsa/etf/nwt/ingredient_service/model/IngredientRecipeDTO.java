package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IngredientRecipeDTO {

    private UUID id;

    private Integer quantity;

    private UUID recipeID;

    @NotNull
    private UUID ingredientRecipeID;

    public IngredientRecipeDTO(Integer quantity, UUID recipeID, UUID ingredientRecipeID) {
        this.quantity = quantity;
        this.recipeID = recipeID;
        this.ingredientRecipeID = ingredientRecipeID;
    }

    public IngredientRecipeDTO() {

    }

}
