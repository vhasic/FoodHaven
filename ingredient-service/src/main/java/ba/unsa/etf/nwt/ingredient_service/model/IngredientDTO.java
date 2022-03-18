package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IngredientDTO {

    private UUID id;

    @Size(max = 255)
    private String name;

    private Integer calorieCount;

    private Integer vitamins;

    private Integer carbohidrates;

    private Integer fat;

    private Integer proteins;

    @Size(max = 50)
    private String measuringUnit;

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
