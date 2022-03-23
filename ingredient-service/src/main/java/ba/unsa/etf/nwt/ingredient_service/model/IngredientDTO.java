package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IngredientDTO {

    private UUID id;

    @NotEmpty(message = "Ingredient name is required")
    @Size(max = 50, message = "Ingredient name can't be longer than 50 characters")
    private String name;

    @NotNull(message = "Number of calories is required")
    private Integer calorieCount;

    @NotNull(message = "Quantity of vitamins is required")
    private Integer vitamins;

    @NotNull(message = "Quantity of carbohidrates is required")
    private Integer carbohidrates;

    @NotNull(message = "Quantity of fat is required")
    private Integer fat;

    @NotNull(message = "Quantity of proteins is required")
    private Integer proteins;

    @Size(max = 50, message = "Measuring unit name can't be longer than 50 characters")
    private String measuringUnit;

    @NotNull
    private UUID ingredientPicture;

    public IngredientDTO(String name, Integer calorieCount, Integer vitamins, Integer carbohidrates,
                      Integer fat, Integer proteins, String measuringUnit, UUID ingredientPicture) {
        this.name = name;
        this.calorieCount = calorieCount;
        this.vitamins = vitamins;
        this.carbohidrates = carbohidrates;
        this.fat = fat;
        this.proteins = proteins;
        this.measuringUnit = measuringUnit;
        this.ingredientPicture = ingredientPicture;
    }

    public IngredientDTO() {

    }

}
